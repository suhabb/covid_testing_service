package uk.ac.kcl.covid.testing.application_service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import uk.ac.kcl.covid.testing.data_service.CovidCaseHistoryDataService;
import uk.ac.kcl.covid.testing.data_service.CovidInfoDataService;
import uk.ac.kcl.covid.testing.data_transfer.CovidCaseCountryDto;
import uk.ac.kcl.covid.testing.data_transfer.CovidInfoDto;
import uk.ac.kcl.covid.testing.domain.CovidCaseCountry;
import uk.ac.kcl.covid.testing.domain.CovidInfo;
import uk.ac.kcl.covid.testing.mapper.Mapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Slf4j
public class CovidInfoApplicationServiceTest {

    private CovidInfoDataService covidInfoDataService = mock(CovidInfoDataService.class);
    private CovidCaseHistoryDataService covidCaseHistoryDataService = mock(CovidCaseHistoryDataService.class);
    private Mapper mapper = new Mapper(new ObjectMapper());
    private CovidInfoApplicationService covidInfoApplicationService;

    @BeforeEach
    public void setUp() {
         covidInfoApplicationService = new CovidInfoApplicationService(mapper,
                covidInfoDataService, covidCaseHistoryDataService);
    }

    @Test
    public void given_iso_code_find_country_mono() throws IOException {
        File resource = new ClassPathResource(
                "json/covid_info.json").getFile();
        String mockJson = new String(Files.readAllBytes(resource.toPath()));

        CovidInfo covidInfo = mapper.mapStringToCovidInfo(mockJson);
        when(covidInfoDataService.findByIsoCode(anyString())).thenReturn(Mono.just(covidInfo));
        Mono<CovidInfoDto> covidInfoMono = covidInfoApplicationService.findByIsoCode("GBR");
        CovidInfoDto covidInfoDto = mapper.mapStringToCovidInfoDto(mockJson);

        StepVerifier
                .create(covidInfoMono)
                .expectNext(covidInfoDto)
                .expectComplete()
                .verify();
    }

    @Test
    public void given_iso_code_find_timeline_mono() throws IOException {
        File resource = new ClassPathResource(
                "json/covid_timeline.json").getFile();
        String mockJson = new String(Files.readAllBytes(resource.toPath()));

        CovidCaseCountry covidCaseCountry = mapper.mapStringToCovidCaseHistory(mockJson);
        when(covidCaseHistoryDataService.findTimelineByIsoCode(anyString())).thenReturn(Mono.just(covidCaseCountry));
        Mono<CovidCaseCountryDto> covidCaseCountryMono = covidInfoApplicationService.findTimeline("GBR");
        CovidCaseCountryDto covidCaseCountryDto = mapper.mapStringToCovidCaseCountryDto(mockJson);
        StepVerifier
                .create(covidCaseCountryMono)
                .expectNext(covidCaseCountryDto)
                .expectComplete()
                .verify();
    }
}


