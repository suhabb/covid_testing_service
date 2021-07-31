package uk.ac.kcl.covid.testing.domain;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "covidStats")
public class CovidCaseCountry {

    @JsonProperty("country")
    @Field
    private String country;

    @JsonProperty("isoCode")
    @Field
    private String isoCode;

    @JsonProperty("timeline")
    @Field
    private Timeline timeline;



}
