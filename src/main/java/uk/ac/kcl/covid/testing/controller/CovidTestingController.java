package uk.ac.kcl.covid.testing.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CovidTestingController {

    @GetMapping
    public String sanity(){
        return "Covid Testing Service : Ok";
    }
}
