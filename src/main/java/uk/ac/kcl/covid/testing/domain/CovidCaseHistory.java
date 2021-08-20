package uk.ac.kcl.covid.testing.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.ac.kcl.covid.testing.data_transfer.CovidStatsDto;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CovidCaseHistory {

    @JsonProperty("continent")
    private String continent;
    @JsonProperty("location")
    private String location;
    @JsonProperty("population")
    private Double population;
    @JsonProperty("population_density")
    private Double populationDensity;
    @JsonProperty("median_age")
    private Double medianAge;
    @JsonProperty("aged_65_older")
    private Double aged65Older;
    @JsonProperty("aged_70_older")
    private Double aged70Older;
    @JsonProperty("gdp_per_capita")
    private Double gdpPerCapita;
    @JsonProperty("cardiovasc_death_rate")
    private Double cardiovascDeathRate;
    @JsonProperty("diabetes_prevalence")
    private Double diabetesPrevalence;
    @JsonProperty("handwashing_facilities")
    private Double handwashingFacilities;
    @JsonProperty("hospital_beds_per_thousand")
    private Double hospitalBedsPerThousand;
    @JsonProperty("life_expectancy")
    private Double lifeExpectancy;
    @JsonProperty("human_development_index")
    private Double humanDevelopmentIndex;
    @JsonProperty("data")
    private List<CovidStatsDto> data = null;
}
