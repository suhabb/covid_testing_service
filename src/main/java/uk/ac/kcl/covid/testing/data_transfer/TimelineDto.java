package uk.ac.kcl.covid.testing.data_transfer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.ac.kcl.covid.testing.domain.Cases;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimelineDto {

    @JsonProperty("cases")
    private List<Cases> cases;

    @JsonProperty("deaths")
    private List<DeathsDto> deaths;

    @JsonProperty("recovered")
    private List<RecoveredDto> recoveredDto;
}
