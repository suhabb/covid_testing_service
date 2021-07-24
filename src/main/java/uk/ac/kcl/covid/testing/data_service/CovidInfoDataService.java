package uk.ac.kcl.covid.testing.data_service;

import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import uk.ac.kcl.covid.testing.domain.CovidInfo;
import uk.ac.kcl.covid.testing.repository.CovidInfoRepository;

import java.util.Optional;

@Service
public class CovidInfoDataService {

    public static final String ISO_CODE = "countryInfo.iso3";
    private CovidInfoRepository covidInfoRepository;

    private ReactiveMongoTemplate reactiveMongoTemplate;

    public CovidInfoDataService(ReactiveMongoTemplate reactiveMongoTemplate,CovidInfoRepository countryRepository){
        this.covidInfoRepository = countryRepository;
        this.reactiveMongoTemplate = reactiveMongoTemplate;
    }

    public Flux<CovidInfo> search(Optional<String> isoCode){

        Criteria criteria = new Criteria();
        if(isoCode.isPresent()){
            String value = isoCode.get();
            criteria.and(ISO_CODE).regex(value,"i");
        }
        Flux<CovidInfo> covidInfoFlux = reactiveMongoTemplate.find(new Query(criteria), CovidInfo.class);
        return covidInfoFlux;
    }

    public Mono<CovidInfo> findByIsoCode(String isoCode){
        return this.covidInfoRepository.findByIsoCode(isoCode);
    }

    public Mono<CovidInfo> save(CovidInfo covidInfo){
        return this.covidInfoRepository.save(covidInfo);
    }

    public Mono<Void> deleteAll(){
        return this.covidInfoRepository.deleteAll();
    }

    public Flux<CovidInfo> findAll(){
        return this.covidInfoRepository.findAll();
    }

    public Mono<CovidInfo> findByCountry(String country){
        return this.covidInfoRepository.findByCountry(country);
    }
}
