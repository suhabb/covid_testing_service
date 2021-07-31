package uk.ac.kcl.covid.testing.data_transfer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "date"
})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecoveredDto {

    @JsonProperty("date")
    private String date;

    @JsonProperty("value")
    private Integer value;



}