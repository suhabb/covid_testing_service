package uk.ac.kcl.covid.testing.data_transfer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CovidCaseHistoryDto {

    @JsonProperty("continent")
    public String continent;
    @JsonProperty("location")
    public String location;
    @JsonProperty("population")
    public Double population;
    @JsonProperty("population_density")
    public Double populationDensity;
    @JsonProperty("median_age")
    public Double medianAge;
    @JsonProperty("aged_65_older")
    public Double aged65Older;
    @JsonProperty("aged_70_older")
    public Double aged70Older;
    @JsonProperty("gdp_per_capita")
    public Double gdpPerCapita;
    @JsonProperty("cardiovasc_death_rate")
    public Double cardiovascDeathRate;
    @JsonProperty("diabetes_prevalence")
    public Double diabetesPrevalence;
    @JsonProperty("handwashing_facilities")
    public Double handwashingFacilities;
    @JsonProperty("hospital_beds_per_thousand")
    public Double hospitalBedsPerThousand;
    @JsonProperty("life_expectancy")
    public Double lifeExpectancy;
    @JsonProperty("human_development_index")
    public Double humanDevelopmentIndex;
    @JsonProperty("data")
    public List<CovidStatsDto> data = null;
}
