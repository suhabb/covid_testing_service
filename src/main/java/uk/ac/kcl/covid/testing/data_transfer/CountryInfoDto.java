package uk.ac.kcl.covid.testing.data_transfer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "_id",
        "iso2",
        "iso3",
        "lat",
        "flag"
})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountryInfoDto {

    @JsonProperty("_id")
    public Integer id;
    @JsonProperty("iso2")
    public String iso2;
    @JsonProperty("iso3")
    public String iso3;
    @JsonProperty("lat")
    public Integer lat;
    @JsonProperty("flag")
    public String flag;

}