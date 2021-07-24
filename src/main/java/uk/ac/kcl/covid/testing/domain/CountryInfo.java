package uk.ac.kcl.covid.testing.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.data.mongodb.core.mapping.Field;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "_id",
        "iso2",
        "iso3",
        "lat",
        "long",
        "flag"
})
public class CountryInfo {

    @JsonProperty("_id")
    @Field
    public Integer id;

    @JsonProperty("iso2")
    @Field
    public String iso2;

    @JsonProperty("iso3")
    @Field
    public String iso3;

    @JsonProperty("lat")
    @Field
    public Integer lat;

    @JsonProperty("flag")
    @Field
    public String flag;

}