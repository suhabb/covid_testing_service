package uk.ac.kcl.covid.testing.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;
import uk.ac.kcl.covid.testing.data_service.CovidCaseHistoryDataService;
import uk.ac.kcl.covid.testing.data_service.CovidInfoDataService;
import uk.ac.kcl.covid.testing.domain.Cases;
import uk.ac.kcl.covid.testing.domain.CovidCaseCountry;
import uk.ac.kcl.covid.testing.domain.CovidInfo;
import uk.ac.kcl.covid.testing.domain.Deaths;
import uk.ac.kcl.covid.testing.domain.Recovered;
import uk.ac.kcl.covid.testing.domain.Timeline;
import uk.ac.kcl.covid.testing.mapper.Mapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

@Component
@Slf4j
public class CovidWebDataService {

    public static final String KEY = "country";
    public static final String TIMELINE = "timeline";
    public static final String RECOVERED = "recovered";
    public static final String CASES = "cases";
    public static final String DEATHS = "deaths";
    private CovidInfoDataService covidInfoDataService;

    private CovidCaseHistoryDataService covidCaseHistoryDataService;

    private Mapper mapper;

    private final WebClient webClient;

    private HashMap<String, String> mapOfIso3Countries = new HashMap<>();

    private List<String> listOfCountries = new ArrayList<>();

    private Environment environment;


    public CovidWebDataService(CovidInfoDataService covidInfoDataService, Mapper mapper, WebClient.Builder webClientBuilder,
                               CovidCaseHistoryDataService covidCaseHistoryDataService, Environment environment) {
        this.covidInfoDataService = covidInfoDataService;
        this.mapper = mapper;
        this.webClient = webClientBuilder.build();
        this.covidCaseHistoryDataService = covidCaseHistoryDataService;
        this.environment = environment;
    }

    @Scheduled(fixedRate = 60 * 60 * 1000)
    public void getUpdatedCovidDataFromApi() throws InterruptedException {
        log.info(" API update started {}:", LocalDate.now());

        Flux<CovidInfo> covidInfoFlux = this.webClient.get()
                .uri("https://corona.lmao.ninja/v3/covid-19/countries")
                .retrieve()
                .bodyToFlux(CovidInfo.class)
                .retryWhen(Retry.backoff(3, Duration.ofSeconds(2)));
        log.info("Deleting All Data {}:", LocalDateTime.now());
        covidInfoDataService.deleteAll().subscribe();
        log.info("Starting to add data from API.............  ,Time:{}", LocalDateTime.now());
        covidInfoFlux.map(covidInfo -> covidInfoDataService.save(covidInfo).subscribe()).subscribe();
    }

    @Scheduled(fixedRate = 60 * 60 * 1000)
    public void getUpdatedCovidTimelineDataFromApi() throws InterruptedException, IOException {
        log.info(" API update started {}:", LocalDate.now());
        List<String> isoCountries = buildListOfIso3Countries();
        covidCaseHistoryDataService.deleteAll().subscribe();
        log.info("Deleting All Data {}:", LocalDateTime.now());
        File resource = new ClassPathResource(
                "json/time_line.json").getFile();
        String mockTimelineJson = new String(Files.readAllBytes(resource.toPath()));

        for (String isoCountry : isoCountries) {
            Mono<String> covidCaseHistoryMono = this.webClient
                    .get()
                    .uri("https://corona.lmao.ninja/v3/covid-19/historical/" + isoCountry + "?lastdays=30")
                    .retrieve()
                    .bodyToMono(String.class)
                    .onErrorReturn(mockTimelineJson);
            covidCaseHistoryMono.map(caseCountry -> {
                JSONObject json = new JSONObject(caseCountry);
                if (!json.isEmpty()) {
                    String country = json.getString(KEY);
                    if (Objects.nonNull(country)) {
                        log.info("Country:{}", new Locale("", isoCountry.substring(0, 2))
                                .getDisplayCountry());
                        CovidCaseCountry covidCaseCountry = new CovidCaseCountry();
                        covidCaseCountry.setCountry(new Locale("", isoCountry.substring(0, 2))
                                .getDisplayCountry());
                        covidCaseCountry.setIsoCode(isoCountry);
                        JSONObject timelineJson = (JSONObject) json.get(TIMELINE);
                        JSONObject jsonRecovered = (JSONObject) timelineJson.get(RECOVERED);
                        JSONObject jsonCases = (JSONObject) timelineJson.get(CASES);
                        JSONObject jsonDeaths = (JSONObject) timelineJson.get(DEATHS);
                        Timeline timeline = new Timeline();
                        List<Recovered> recoveredList = new ArrayList<>();
                        for (String key : jsonRecovered.keySet()) {
                            Recovered recovered = new Recovered();
                            Integer val = (Integer) jsonRecovered.get(key);
                            recovered.setDate(formatDate(key));
                            recovered.setValue(val);
                            recoveredList.add(recovered);
                        }
                        timeline.setRecovered(recoveredList);
                        List<Cases> casesList = new ArrayList<>();
                        for (String key : jsonCases.keySet()) {
                            Cases cases = new Cases();
                            Integer val = (Integer) jsonCases.get(key);
                            cases.setDate(formatDate(key));
                            cases.setValue(val);
                            casesList.add(cases);
                        }
                        timeline.setCases(casesList);
                        List<Deaths> deathsList = new ArrayList<>();
                        for (String key : jsonDeaths.keySet()) {
                            Deaths deaths = new Deaths();
                            Integer val = (Integer) jsonDeaths.get(key);
                            deaths.setDate(formatDate(key));
                            deaths.setValue(val);
                            deathsList.add(deaths);
                        }
                        timeline.setDeaths(deathsList);
                        covidCaseCountry.setTimeline(timeline);
                        covidCaseHistoryDataService.save(covidCaseCountry).subscribe();
                        log.info("Saving All Data {}:", LocalDateTime.now());
                    }
                }
                return caseCountry;
            }).subscribe();
        }
    }

    private String formatDate(String key) {
        try {
            Date dateKey = new SimpleDateFormat("MM/dd/yy").parse(key);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.format(dateKey);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public HashMap<String, String> buildMapOfIso3Countries() {

        String[] countryCodes = Locale.getISOCountries();
        for (String cc : countryCodes) {
            // country name , country code map
            Locale locale = new Locale("", cc);
            mapOfIso3Countries.put(new Locale("", cc).getDisplayCountry(), locale.getISO3Country()
                    .toUpperCase());
            mapOfIso3Countries.put("USA", "USA");
            mapOfIso3Countries.put("UK", "GBR");
        }
        return mapOfIso3Countries;
    }

    public List<String> buildListOfIso3Countries() {

        String[] countryCodes = Locale.getISOCountries();
        for (String cc : countryCodes) {
            // country name , country code map
            Locale locale = new Locale("", cc);
            listOfCountries.add(locale.getISO3Country()
                    .toUpperCase());
        }
        return listOfCountries;
    }
}
