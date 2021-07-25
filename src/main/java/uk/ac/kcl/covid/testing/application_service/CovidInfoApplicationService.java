package uk.ac.kcl.covid.testing.application_service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import uk.ac.kcl.covid.testing.data_service.CovidInfoDataService;
import uk.ac.kcl.covid.testing.data_transfer.CovidInfoDto;
import uk.ac.kcl.covid.testing.domain.CovidInfo;
import uk.ac.kcl.covid.testing.mapper.Mapper;

import java.util.Date;

@Service
public class CovidInfoApplicationService {

    private CovidInfoDataService covidInfoDataService;
    private Mapper mapper;

    public CovidInfoApplicationService(Mapper mapper, CovidInfoDataService covidInfoDataService) {
        this.covidInfoDataService = covidInfoDataService;
        this.mapper = mapper;

    }

    public Mono<CovidInfoDto> findByIsoCode(String isoCode) {
        Mono<CovidInfo> countryMono = this.covidInfoDataService.findByIsoCode(isoCode);
        countryMono.map(c-> new Date(c.getUpdated()));
        return countryMono.map(c -> mapper.readValue(c, CovidInfoDto.class));
    }

    public Flux<CovidInfoDto> findAll(){
        Flux<CovidInfo> covidInfoFlux = this.covidInfoDataService.findAll();
        return covidInfoFlux.collectList().map(mapper::mapToCovidInfoDtoList).flatMapMany(Flux::fromIterable);
    }
}
