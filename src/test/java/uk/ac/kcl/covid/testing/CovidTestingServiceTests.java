package uk.ac.kcl.covid.testing;

import org.junit.jupiter.api.Test;

import java.util.Locale;

class CovidTestingServiceTests {


	@Test
	public void test(){
		Locale l = new Locale("", "in");
		String c = "IND".substring(0,2);
		System.out.println("CovidTestingServiceTests.test"+c);
		System.out.println("CovidTestingServiceTests.test:"+new Locale("", "IND".substring(0,2))
				.getDisplayCountry());
	}

}
