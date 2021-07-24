package uk.ac.kcl.covid.testing.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.ac.kcl.covid.testing.data_transfer.CovidInfoDto;
import uk.ac.kcl.covid.testing.domain.CovidInfo;

import java.io.IOException;
import java.util.List;

@Component
@Slf4j
public class Mapper {

    @Autowired
    public ObjectMapper objectMapper;

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

    public List<CovidInfo> mapToCovidInfoList(List<CovidInfoDto> covidInfoDtoList) {

        try {
            return objectMapper.readValue(writeValueAsString(covidInfoDtoList), new TypeReference<List<CovidInfo>>(){});
        } catch (IOException exception) {
            log.debug("Json exception read value method", exception);
            throw new RuntimeException(exception);
        }
    }

    public CovidInfoDto mapToCovidInfo(CovidInfo covidInfo) {

        try {
            return objectMapper.readValue(writeValueAsString(covidInfo), CovidInfoDto.class);
        } catch (IOException exception) {
            log.debug("Json exception read value method", exception);
            throw new RuntimeException(exception);
        }
    }
    public CovidInfo mapToCovidInfo(CovidInfoDto covidInfoDto) {

        try {
            return objectMapper.readValue(writeValueAsString(covidInfoDto), CovidInfo.class);
        } catch (IOException exception) {
            log.debug("Json exception read value method", exception);
            throw new RuntimeException(exception);
        }
    }




    public List<CovidInfoDto> mapStringToCovidInfoList(String jsonString) {

        try {
            return objectMapper.readValue(jsonString,new TypeReference<List<CovidInfoDto>>(){});
        } catch (IOException exception) {
            log.debug("Json exception read value method", exception);
            throw new RuntimeException(exception);
        }
    }
}