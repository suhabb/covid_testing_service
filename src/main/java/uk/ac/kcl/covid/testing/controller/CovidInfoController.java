package uk.ac.kcl.covid.testing.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import uk.ac.kcl.covid.testing.application_service.CovidInfoApplicationService;
import uk.ac.kcl.covid.testing.data_transfer.CovidInfoDto;

import java.util.List;
import java.util.Optional;

@RestController
public class CovidInfoController {

    private CovidInfoApplicationService covidInfoApplicationService;

    public CovidInfoController(CovidInfoApplicationService covidInfoApplicationService){
        this.covidInfoApplicationService = covidInfoApplicationService;
    }

    @GetMapping("/covid/search")
    public Mono<ResponseEntity<List<CovidInfoDto>>> search(@RequestParam("iso-code") Optional<String> isoCode) {

      //  Flux<CovidInfoDto> fluxCountryDto = covidInfoApplicationService.search(isoCode);
       // return fluxCountryDto.collectList().map(ResponseEntity::ok);

        return null;
    }

    @GetMapping("/testing/iso-code/{isoCode}")
    public Mono<ResponseEntity<CovidInfoDto>> findByIsoCode(@PathVariable("isoCode") String isoCode) {
        Mono<CovidInfoDto> monoCountryDto = covidInfoApplicationService.findByIsoCode(isoCode);
        return monoCountryDto.map(ResponseEntity::ok );
    }

    @GetMapping
    public String sanity(){
        return "Covid Testing Service : Ok";
    }
}
