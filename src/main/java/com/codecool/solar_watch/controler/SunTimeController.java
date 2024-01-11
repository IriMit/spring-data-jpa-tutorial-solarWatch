package com.codecool.solar_watch.controler;

//import com.codecool.solar_watch.model.GeoParser;
import com.codecool.solar_watch.model.ResultOfGEoParser;
import com.codecool.solar_watch.model.SunTimeReport;
import com.codecool.solar_watch.service.OpenSunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.InvalidParameterException;
import java.util.logging.Logger;

@RestController
public class SunTimeController {
    @Autowired
    private final OpenSunService openSunService;


    @Autowired
    public SunTimeController(OpenSunService openSunService) {
        this.openSunService = openSunService;
    }

    @GetMapping("/")
    public String SayHello() {
        return "Hello World";
    }

    @GetMapping("/suntime")

    public SunTimeReport getSunTimes(@RequestParam(required = true) String date,
                                     @RequestParam(required = true) String cityName) {

        if (date == null || date.isEmpty()) {
            throw new InvalidDateException();
        }

        ResultOfGEoParser resultOfGEoParser = openSunService.getLatLongByCityName(cityName);
        if (resultOfGEoParser == null) {
            throw new InvalidCityException();
        }
            String lat = String.valueOf(resultOfGEoParser.lat());
            String lng = String.valueOf(resultOfGEoParser.lon());
            return openSunService.getSunTimeForCityByDate(date, lat, lng);

    }
}
