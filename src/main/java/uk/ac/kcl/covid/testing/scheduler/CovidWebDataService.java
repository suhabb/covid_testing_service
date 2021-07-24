package uk.ac.kcl.covid.testing.scheduler;


import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.util.retry.Retry;
import uk.ac.kcl.covid.testing.data_service.CovidInfoDataService;
import uk.ac.kcl.covid.testing.domain.CovidInfo;
import uk.ac.kcl.covid.testing.mapper.Mapper;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;

@Component
@Slf4j
public class CovidWebDataService {

    private CovidInfoDataService covidInfoDataService;

    private Mapper mapper;

    private final WebClient webClient;

    private HashMap<String, String> mapOfIso3Countries = new HashMap<>();

    private Environment environment;


    public CovidWebDataService(CovidInfoDataService covidInfoDataService, Mapper mapper, WebClient.Builder webClientBuilder,
                               Environment environment) {
        this.covidInfoDataService = covidInfoDataService;
        this.mapper = mapper;
        this.webClient = webClientBuilder.baseUrl("https://corona.lmao.ninja").build();
        this.environment = environment;
    }

    @Scheduled(fixedRate = 60 * 60 * 1000)
    public void getUpdatedVaccineDataFromApi() throws InterruptedException {
        log.info(" API update started {}:", LocalDate.now());

        Flux<CovidInfo> covidInfoFlux = this.webClient.get()
                .uri("/v3/covid-19/countries")
                .retrieve()
                .bodyToFlux(CovidInfo.class)
                .retryWhen(Retry.backoff(3, Duration.ofSeconds(2)));
        log.info("Deleting All Data {}:", LocalDateTime.now());
        covidInfoDataService.deleteAll().subscribe();
        log.info("Starting to add data from API.............  ,Time:{}", LocalDateTime.now());
        covidInfoFlux.map(covidInfo -> covidInfoDataService.save(covidInfo).subscribe()).subscribe();
    }
}
