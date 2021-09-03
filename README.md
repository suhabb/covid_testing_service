# Covid Testing Service

Covid Testing Service is a Reactive Spring Boot Microservice which returns the data of covid testing/timelines/cases of each country.
The data source used is publicly available in api https://corona.lmao.ninja/ (Open Disease Data API). 

- Covid Testing service runs on the port 8081
  `` http://localhost:8081``

## Prerequisites

What things you need to install the software and how to install them

```
- IntelliJ(Optional)
- JDK11
- Maven
- NoSqlbooster(Optional)
- Database
  - MongoDb  
  - Run the script for creating database and collection in mongodb
```
### Databse Scripts

  ```
    use covid_testing_db;
    db.createCollection("covidInfo");
    db.createCollection("covidStats");
    db.covidStats.createIndex({" countryInfo.iso3": 1 }, { unique: true } );
    db.covidInfo.createIndex({"isoCode": 1 }, { unique: true } );

  ```
## Start the spring boot service from root folder of the project
  - mvn clean package
  - java -jar target/covid_testing_service-0.0.1-SNAPSHOT.jar
   * ``(Or)``
  - mvn spring-boot:run
   * ``(Or)``
  - Can import in IntelliJ and run as main application by adding the Main file

## API
  - http://localhost:8081/covid-testing-service/testing/iso-code/{iso-code}
  - http://localhost:8081/covid-testing-service/testing/timeline/{iso-code}
  
  - Repalce iso-code with isocode3 of countrties like GBR,IND.
  - Example : http://localhost:8081/covid-testing-service/testing/timeline/GBR

## Diagnostics

1. Check for jdk version as it requires JDK 11
      - <terminal>> java -version
2.  Check if mongo service is up:
      - mongod --dbpath <your-path>/data/db
       
## Contributing

 - Suhail Mir
  
 ## References
  - https://start.spring.io/
  - https://spring.io/reactive
  
 ## Dataset
    - https://corona.lmao.ninja/v3/covid-19/countries
