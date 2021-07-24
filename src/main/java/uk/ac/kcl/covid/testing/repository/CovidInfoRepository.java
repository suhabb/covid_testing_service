package uk.ac.kcl.covid.testing.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import uk.ac.kcl.covid.testing.domain.CovidInfo;



public interface CovidInfoRepository extends ReactiveMongoRepository<CovidInfo, String> {

    Mono<CovidInfo> findByCountry(String country);

    @Query("{ 'countryInfo.iso3' : {$regex : ?0, $options: 'i'}}")
    Mono<CovidInfo> findByIsoCode(String isoCode);

    Flux<CovidInfo> findAll();
}