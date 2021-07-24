package uk.ac.kcl.covid.testing.data_transfer;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CovidInfoDto {

    @JsonProperty("updated")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public Date updated;
    @JsonProperty("country")
    public String country;
    @JsonProperty("countryInfo")
    public CountryInfoDto countryInfo;
    @JsonProperty("cases")
    public Integer cases;
    @JsonProperty("todayCases")
    public Integer todayCases;
    @JsonProperty("deaths")
    public Integer deaths;
    @JsonProperty("todayDeaths")
    public Integer todayDeaths;
    @JsonProperty("recovered")
    public Integer recovered;
    @JsonProperty("todayRecovered")
    public Integer todayRecovered;
    @JsonProperty("active")
    public Integer active;
    @JsonProperty("critical")
    public Integer critical;
    @JsonProperty("casesPerOneMillion")
    public Integer casesPerOneMillion;
    @JsonProperty("deathsPerOneMillion")
    public Integer deathsPerOneMillion;
    @JsonProperty("tests")
    public Integer tests;
    @JsonProperty("testsPerOneMillion")
    public Integer testsPerOneMillion;
    @JsonProperty("population")
    public Integer population;
    @JsonProperty("continent")
    public String continent;
    @JsonProperty("oneCasePerPeople")
    public Integer oneCasePerPeople;
    @JsonProperty("oneDeathPerPeople")
    public Integer oneDeathPerPeople;
    @JsonProperty("oneTestPerPeople")
    public Integer oneTestPerPeople;
    @JsonProperty("activePerOneMillion")
    public Double activePerOneMillion;
    @JsonProperty("recoveredPerOneMillion")
    public Double recoveredPerOneMillion;
    @JsonProperty("criticalPerOneMillion")
    public Double criticalPerOneMillion;

}