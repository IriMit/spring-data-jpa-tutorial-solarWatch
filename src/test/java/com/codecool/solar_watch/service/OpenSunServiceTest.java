package com.codecool.solar_watch.service;

import com.codecool.solar_watch.model.ResultOfGEoParser;
import com.codecool.solar_watch.model.SunTimeReport;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

class OpenSunServiceTest {
    RestTemplate restTemplate = new RestTemplate();
    OpenSunService openSunService = new OpenSunService(restTemplate);
    @Test
    void getLatLongByCityName() {
        //London

        double expectedLat = 51.5073219;
        double expectedLon = -0.1276474;
        ResultOfGEoParser expected = new ResultOfGEoParser(expectedLat, expectedLon);

        ResultOfGEoParser result =  openSunService.getLatLongByCityName("London");
        assertNotNull (result);
        assertEquals(expected, result);


    }

    @Test
    void getSunTimeForCityByDate() {
        SunTimeReport sunTimeReportResult = openSunService.getSunTimeForCityByDate("2023-06-06", "51.5073219", "-0.1276474" );
        SunTimeReport expected = new SunTimeReport("3:43:27 AM", "8:14:52 PM");

        assertEquals(expected, sunTimeReportResult);
    }
}