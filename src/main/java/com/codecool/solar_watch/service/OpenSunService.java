package com.codecool.solar_watch.service;

//import com.codecool.solar_watch.model.GeoParser;
import com.codecool.solar_watch.model.ResultOfGEoParser;
import com.codecool.solar_watch.model.SunTimeReport;
import com.codecool.solar_watch.model.SunTimeApiResults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.OffsetDateTime;
import java.util.Arrays;

@Service
public class OpenSunService {
    private static final Logger logger = LoggerFactory.getLogger(OpenSunService.class);
    private static final String API_KEYForOpenWeather = "7a76cf5564e74fec2f45425b1f41c6f5";
    private final RestTemplate restTemplate;

    public OpenSunService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResultOfGEoParser  getLatLongByCityName(String cityName){
        if(cityName == null || cityName.isEmpty()){
            throw new IllegalArgumentException("city name is required");
        }
        String  url = String.format("http://api.openweathermap.org/geo/1.0/direct?q=%s&limit=%s&appid=%s",
                cityName, 1,  API_KEYForOpenWeather);
        ResultOfGEoParser[] response = restTemplate.getForObject(url, ResultOfGEoParser[].class);

        if (response!= null){
            logger.info("responseGeo:{}", response[0]);
            var lon = response[0].lon();
            var lat = response[0].lat();
            return new ResultOfGEoParser(lat, lon);
        }
        return null;
    };

    public SunTimeReport getSunTimeForCityByDate(String date, String lat, String lng) {


        String url = String.format("https://api.sunrise-sunset.org/json?lat=%s&lng=%s&date=%s", lat, lng, date);

        ResponseEntity<SunTimeApiResults> response = restTemplate.getForEntity(url, SunTimeApiResults.class );

        if (response.getBody() != null) {
            logger.info("response: {}", response);
            //OffsetDateTime sunriseTime = OffsetDateTime.parse(response.getBody().results().sunrise());
            return new SunTimeReport( response.getBody().results().sunrise(), response.getBody().results().sunset());
        } else {
            logger.error("Response body is null.");
        }
        return null;
    }
}




