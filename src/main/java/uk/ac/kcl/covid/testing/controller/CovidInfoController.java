package uk.ac.kcl.covid.testing.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import uk.ac.kcl.covid.testing.application_service.CovidInfoApplicationService;
import uk.ac.kcl.covid.testing.data_transfer.CovidCaseCountryDto;
import uk.ac.kcl.covid.testing.data_transfer.CovidInfoDto;

import java.util.List;

@RestController
public class CovidInfoController {

    private CovidInfoApplicationService covidInfoApplicationService;

    public CovidInfoController(CovidInfoApplicationService covidInfoApplicationService){
        this.covidInfoApplicationService = covidInfoApplicationService;
    }

    @GetMapping("/testing/all")
    public Mono<ResponseEntity<List<CovidInfoDto>>> findAll() {
        Flux<CovidInfoDto> covidInfoDtoFlux = covidInfoApplicationService.findAll();
        return covidInfoDtoFlux.collectList().map(ResponseEntity::ok);
    }

    @GetMapping("/testing/iso-code/{isoCode}")
    public Mono<ResponseEntity<CovidInfoDto>> findByIsoCode(@PathVariable("isoCode") String isoCode) {
        Mono<CovidInfoDto> monoCountryDto = covidInfoApplicationService.findByIsoCode(isoCode);
        return monoCountryDto.map(ResponseEntity::ok );
    }

    @GetMapping("/testing/timeline/{isoCode}")
    public Mono<ResponseEntity<CovidCaseCountryDto>> findTimelineIsoCode(@PathVariable("isoCode") String isoCode) {
        Mono<CovidCaseCountryDto> covidCaseCountryMono = covidInfoApplicationService.findTimeline(isoCode);
        return covidCaseCountryMono.map(ResponseEntity::ok );
    }

    @GetMapping
    public String sanity(){
        return "Covid Testing Service : Ok";
    }
}
