package uk.ac.kcl.covid.testing.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import uk.ac.kcl.covid.testing.domain.CovidCaseCountry;

public interface CovidCaseHistoryRepository extends ReactiveMongoRepository<CovidCaseCountry, String>  {

    Mono<CovidCaseCountry> findByCountry(String country);
    Flux<CovidCaseCountry> findAll();

    @Query("{ 'isoCode' : {$regex : ?0, $options: 'i'}}")
    Mono<CovidCaseCountry> findTimelineByIsoCode(String isoCode);
}
