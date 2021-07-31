package uk.ac.kcl.covid.testing.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "date"
})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Deaths {


    @Field
    private String date;

    @Field
    private Integer value;

}