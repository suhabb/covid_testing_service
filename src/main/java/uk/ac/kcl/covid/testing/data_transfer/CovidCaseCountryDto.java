package uk.ac.kcl.covid.testing.data_transfer;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.ac.kcl.covid.testing.domain.Timeline;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CovidCaseCountryDto {

    @JsonProperty("country")
    private String country;

    @JsonProperty("isoCode")
    private String isoCode;

    @JsonProperty("timeline")
    private Timeline timeline;

}
