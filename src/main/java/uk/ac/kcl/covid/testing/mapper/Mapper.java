package uk.ac.kcl.covid.testing.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import uk.ac.kcl.covid.testing.data_transfer.CovidCaseCountryDto;
import uk.ac.kcl.covid.testing.data_transfer.CovidInfoDto;
import uk.ac.kcl.covid.testing.domain.CovidCaseCountry;
import uk.ac.kcl.covid.testing.domain.CovidInfo;

import java.io.IOException;
import java.util.List;

@Component
@Slf4j
public class Mapper {

    private ObjectMapper objectMapper;

    public Mapper(ObjectMapper objectMapper){
        this.objectMapper = objectMapper;
    }

    public <T> T readValue(Object object, Class<T> clazz) {
        try {
            return objectMapper.readValue(writeValueAsString(object), clazz);
        } catch (IOException exception) {
            log.debug("Json exception read value method", exception);
            throw new RuntimeException(exception);
        }
    }

    public String writeValueAsString(Object clazz) {
        try {
            return objectMapper.writeValueAsString(clazz);
        } catch (JsonProcessingException exception) {
            log.debug("Json exception write value as string method", exception);
            throw new RuntimeException(exception);
        }
    }

    public String writeValueAsString(List<CovidInfo> covidInfoList) {
        try {
            return objectMapper.writeValueAsString(covidInfoList);
        } catch (JsonProcessingException exception) {
            log.debug("Json exception write value as string method", exception);
            throw new RuntimeException(exception);
        }
    }


    public List<CovidInfoDto> mapToCovidInfoDtoList(List<CovidInfo> countryList) {
        try {
            return objectMapper.readValue(writeValueAsString(countryList), new TypeReference<List<CovidInfoDto>>(){});
        } catch (IOException exception) {
            log.debug("Json exception read value method", exception);
            throw new RuntimeException(exception);
        }
    }

    public CovidInfoDto mapStringToCovidInfoDto(String covidInfo) {
        try {
            return objectMapper.readValue(covidInfo, CovidInfoDto.class);
        } catch (IOException exception) {
            log.debug("Json exception read value method", exception);
            throw new RuntimeException(exception);
        }
    }

    public CovidInfo mapStringToCovidInfo(String covidInfo) {
        try {
            return objectMapper.readValue(covidInfo, CovidInfo.class);
        } catch (IOException exception) {
            log.debug("Json exception read value method", exception);
            throw new RuntimeException(exception);
        }
    }

    public List<CovidInfoDto> mapToCovidInfoList(List<CovidInfo> covidInfoList) {
        try {
            return objectMapper.readValue(writeValueAsString(covidInfoList), new TypeReference<List<CovidInfoDto>>(){});
        } catch (IOException exception) {
            log.debug("Json exception read value method", exception);
            throw new RuntimeException(exception);
        }
    }

    public CovidCaseCountryDto mapToCovidCaseCountry(CovidCaseCountry covidCaseCountry) {
        try {
            return objectMapper.readValue(writeValueAsString(covidCaseCountry), CovidCaseCountryDto.class);
        } catch (IOException exception) {
            log.debug("Json exception read value method", exception);
            throw new RuntimeException(exception);
        }
    }

    public CovidCaseCountry mapStringToCovidCaseHistory(String covidCaseCountry) {
        try {
            return objectMapper.readValue(covidCaseCountry, CovidCaseCountry.class);
        } catch (IOException exception) {
            log.debug("Json exception read value method", exception);
            throw new RuntimeException(exception);
        }
    }

    public CovidCaseCountryDto mapStringToCovidCaseCountryDto(String covidCaseCountry) {
        try {
            return objectMapper.readValue(covidCaseCountry, CovidCaseCountryDto.class);
        } catch (IOException exception) {
            log.debug("Json exception read value method", exception);
            throw new RuntimeException(exception);
        }
    }
}