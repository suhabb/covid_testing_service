package uk.ac.kcl.covid.testing.data_transfer;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "date",
        "total_cases",
        "new_cases",
        "new_cases_smoothed",
        "total_deaths",
        "new_deaths",
        "new_deaths_smoothed",
        "total_cases_per_million",
        "new_cases_per_million",
        "new_cases_smoothed_per_million",
        "total_deaths_per_million",
        "new_deaths_per_million",
        "new_deaths_smoothed_per_million",
        "total_vaccinations",
        "people_vaccinated",
        "people_fully_vaccinated",
        "new_vaccinations",
        "new_vaccinations_smoothed",
        "total_vaccinations_per_hundred",
        "people_vaccinated_per_hundred",
        "people_fully_vaccinated_per_hundred",
        "new_vaccinations_smoothed_per_million"
})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CovidStatsDto {

    @JsonProperty("date")
    @Field
    public String date;
    @JsonProperty("total_cases")
    public Double totalCases;
    @JsonProperty("new_cases")
    public Double newCases;
    @JsonProperty("new_cases_smoothed")
    public Double newCasesSmoothed;
    @JsonProperty("total_deaths")
    public Double totalDeaths;
    @JsonProperty("new_deaths")
    public Double newDeaths;
    @JsonProperty("new_deaths_smoothed")
    public Double newDeathsSmoothed;
    @JsonProperty("total_cases_per_million")
    public Double totalCasesPerMillion;
    @JsonProperty("new_cases_per_million")
    public Double newCasesPerMillion;
    @JsonProperty("new_cases_smoothed_per_million")
    public Double newCasesSmoothedPerMillion;
    @JsonProperty("total_deaths_per_million")
    public Double totalDeathsPerMillion;
    @JsonProperty("new_deaths_per_million")
    public Double newDeathsPerMillion;
    @JsonProperty("new_deaths_smoothed_per_million")
    public Double newDeathsSmoothedPerMillion;
    @JsonProperty("total_vaccinations")
    public Double totalVaccinations;
    @JsonProperty("people_vaccinated")
    public Double peopleVaccinated;
    @JsonProperty("people_fully_vaccinated")
    public Double peopleFullyVaccinated;
    @JsonProperty("new_vaccinations")
    public Double newVaccinations;
    @JsonProperty("new_vaccinations_smoothed")
    public Double newVaccinationsSmoothed;
    @JsonProperty("total_vaccinations_per_hundred")
    public Double totalVaccinationsPerHundred;
    @JsonProperty("people_vaccinated_per_hundred")
    public Double peopleVaccinatedPerHundred;
    @JsonProperty("people_fully_vaccinated_per_hundred")
    public Double peopleFullyVaccinatedPerHundred;
    @JsonProperty("new_vaccinations_smoothed_per_million")
    public Double newVaccinationsSmoothedPerMillion;

}

