package uk.ac.kcl.covid.testing.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import uk.ac.kcl.covid.testing.application_service.CovidInfoApplicationService;
import uk.ac.kcl.covid.testing.data_transfer.CovidCaseCountryDto;
import uk.ac.kcl.covid.testing.data_transfer.CovidInfoDto;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Slf4j
public class CovidInfoControllerTest {

    public CovidInfoApplicationService covidInfoApplicationService = mock(CovidInfoApplicationService.class);

    private CovidInfoController covidInfoController;

    @BeforeEach
    public void setUp(){
         covidInfoController = new CovidInfoController(covidInfoApplicationService);
    }

    @Test
    public void given_uri_return_response_covid_info_entity_mono() throws IOException {
        File resource = new ClassPathResource(
                "json/covid_info.json").getFile();
        String mockJson = new String(Files.readAllBytes(resource.toPath()));
        CovidInfoDto covidInfoDto = mapToCovidInfoDto(mockJson);
        when(covidInfoApplicationService.findByIsoCode(anyString())).thenReturn(Mono.just(covidInfoDto));
        Mono<ResponseEntity<CovidInfoDto>> entityMono = covidInfoController.findByIsoCode("GBR");
        StepVerifier
                .create(entityMono)
                .expectNext(ResponseEntity.ok(covidInfoDto))
                .expectComplete()
                .verify();

    }

    @Test
    public void given_uri_return_response_timeline_entity_mono() throws IOException {
        File resource = new ClassPathResource(
                "json/covid_timeline.json").getFile();
        String mockJson = new String(Files.readAllBytes(resource.toPath()));
        CovidCaseCountryDto covidCaseCountryDto = mapToCovidCaseCountryDto(mockJson);
        when(covidInfoApplicationService.findTimeline(anyString())).thenReturn(Mono.just(covidCaseCountryDto));
        Mono<ResponseEntity<CovidCaseCountryDto>> entityMono = covidInfoController.findTimelineIsoCode("GBR");
        StepVerifier
                .create(entityMono)
                .expectNext(ResponseEntity.ok(covidCaseCountryDto))
                .expectComplete()
                .verify();

    }

    public CovidInfoDto mapToCovidInfoDto(String covidInfo) {

        try {
            return new ObjectMapper().readValue(covidInfo, CovidInfoDto.class);
        } catch (IOException exception) {
            log.debug("Json exception read value method", exception);
            throw new RuntimeException(exception);
        }
    }

    public CovidCaseCountryDto mapToCovidCaseCountryDto(String covidCaseCountry) {

        try {
            return new ObjectMapper().readValue(covidCaseCountry, CovidCaseCountryDto.class);
        } catch (IOException exception) {
            log.debug("Json exception read value method", exception);
            throw new RuntimeException(exception);
        }
    }
}
