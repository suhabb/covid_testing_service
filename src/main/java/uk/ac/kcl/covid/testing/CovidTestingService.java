package uk.ac.kcl.covid.testing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CovidTestingService {

	public static void main(String[] args) {
		SpringApplication.run(CovidTestingService.class, args);
	}

}
