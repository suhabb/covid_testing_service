package uk.ac.kcl.covid.testing.data_transfer;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CovidCaseCountryDto {

    @JsonProperty("country")
    private CovidCaseHistoryDto country;

}
