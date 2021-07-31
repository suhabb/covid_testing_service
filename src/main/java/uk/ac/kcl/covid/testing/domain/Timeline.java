package uk.ac.kcl.covid.testing.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Timeline {

    @JsonProperty("cases")
    @Field
    private List<Cases> cases;

    @JsonProperty("deaths")
    @Field
    private List<Deaths> deaths;

    @JsonProperty("recovered")
    @Field
    private List<Recovered> recovered;
}
