package uk.ac.kcl.covid.testing.data_service;

import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import uk.ac.kcl.covid.testing.domain.CovidCaseCountry;
import uk.ac.kcl.covid.testing.repository.CovidCaseHistoryRepository;

@Service
public class CovidCaseHistoryDataService {

    private CovidCaseHistoryRepository covidCaseHistoryRepository;

    private ReactiveMongoTemplate reactiveMongoTemplate;

    public CovidCaseHistoryDataService(ReactiveMongoTemplate reactiveMongoTemplate, CovidCaseHistoryRepository
            covidCaseHistoryRepository) {
        this.covidCaseHistoryRepository = covidCaseHistoryRepository;
        this.reactiveMongoTemplate = reactiveMongoTemplate;
    }

    public Mono<CovidCaseCountry> findTimelineByIsoCode(String isoCode) {
        return this.covidCaseHistoryRepository.findTimelineByIsoCode(isoCode);
    }

    public Mono<CovidCaseCountry> save(CovidCaseCountry covidCaseCountry) {
        return this.covidCaseHistoryRepository.save(covidCaseCountry);
    }

    public Mono<Void> deleteAll() {
        return this.covidCaseHistoryRepository.deleteAll();
    }
}
