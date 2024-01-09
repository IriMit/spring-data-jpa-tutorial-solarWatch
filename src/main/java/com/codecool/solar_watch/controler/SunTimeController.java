package com.codecool.solar_watch.controler;

import com.codecool.solar_watch.model.GeoParser;
import com.codecool.solar_watch.model.ResultOfGEoParser;
import com.codecool.solar_watch.model.SunTimeReport;
import com.codecool.solar_watch.service.OpenSunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SunTimeController {
    private final OpenSunService openSunService;


    @Autowired
    public SunTimeController(OpenSunService openSunService){
        this.openSunService = openSunService;
    }
    @GetMapping("/")
    public String SayHello(){
        return "Hello World";
    }
    @GetMapping("/suntime")

    public SunTimeReport getSunTimes(@RequestParam String date,
                                     @RequestParam (required = false)String cityName){
      ResultOfGEoParser resultOfGEoParser = openSunService.getLatLongByCityName(cityName);

        System.out.println(resultOfGEoParser);
        String lat = String.valueOf(resultOfGEoParser.lat());
        String lng = String.valueOf(resultOfGEoParser.lon());

        return openSunService.getWeatherResponseForCityByDate(date, lat, lng);
    }
}
