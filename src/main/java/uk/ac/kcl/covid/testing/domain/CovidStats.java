package uk.ac.kcl.covid.testing.domain;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CovidStats {

    @JsonProperty("date")
    @Field
    public Date date;
    @JsonProperty("total_cases")
    @Field
    public Double totalCases;
    @Field
    @JsonProperty("new_cases")
    public Double newCases;
    @JsonProperty("new_cases_smoothed")
    @Field
    public Double newCasesSmoothed;
    @JsonProperty("total_deaths")
    @Field
    public Double totalDeaths;
    @JsonProperty("new_deaths")
    @Field
    public Double newDeaths;
    @JsonProperty("new_deaths_smoothed")
    @Field
    public Double newDeathsSmoothed;
    @JsonProperty("total_cases_per_million")
    @Field
    public Double totalCasesPerMillion;
    @JsonProperty("new_cases_per_million")
    @Field
    public Double newCasesPerMillion;
    @JsonProperty("new_cases_smoothed_per_million")
    @Field
    public Double newCasesSmoothedPerMillion;
    @JsonProperty("total_deaths_per_million")
    @Field
    public Double totalDeathsPerMillion;
    @JsonProperty("new_deaths_per_million")
    @Field
    public Double newDeathsPerMillion;
}

