package uk.ac.kcl.covid.testing.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(value = "covidInfo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CovidInfo {

    @JsonProperty("updated")
    @Field
    public Long updated;

    @JsonProperty("country")
    @Field
    public String country;

    @JsonProperty("countryInfo")
    @Field
    public CountryInfo countryInfo;

    @JsonProperty("cases")
    @Field
    public Integer cases;

    @JsonProperty("todayCases")
    @Field
    public Integer todayCases;

    @JsonProperty("deaths")
    @Field
    public Integer deaths;

    @JsonProperty("todayDeaths")
    @Field
    public Integer todayDeaths;

    @JsonProperty("recovered")
    @Field
    public Integer recovered;

    @JsonProperty("todayRecovered")
    @Field
    public Integer todayRecovered;

    @JsonProperty("active")
    @Field
    public Integer active;

    @JsonProperty("critical")
    @Field
    public Integer critical;

    @JsonProperty("casesPerOneMillion")
    @Field
    public Integer casesPerOneMillion;

    @JsonProperty("deathsPerOneMillion")
    @Field
    public Integer deathsPerOneMillion;

    @JsonProperty("tests")
    @Field
    public Integer tests;

    @JsonProperty("testsPerOneMillion")
    @Field
    public Integer testsPerOneMillion;

    @JsonProperty("population")
    @Field
    public Integer population;

    @JsonProperty("continent")
    @Field
    public String continent;

    @JsonProperty("oneCasePerPeople")
    @Field
    public Integer oneCasePerPeople;

    @JsonProperty("oneDeathPerPeople")
    @Field
    public Integer oneDeathPerPeople;

    @JsonProperty("oneTestPerPeople")
    @Field
    public Integer oneTestPerPeople;

    @JsonProperty("activePerOneMillion")
    @Field
    public Double activePerOneMillion;

    @JsonProperty("recoveredPerOneMillion")
    @Field
    public Double recoveredPerOneMillion;

    @JsonProperty("criticalPerOneMillion")
    @Field
    public Double criticalPerOneMillion;
}
