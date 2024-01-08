package com.codecool.solar_watch.controler;

import com.codecool.solar_watch.model.SunTimeReport;
import com.codecool.solar_watch.service.OpenSunService;
import org.springframework.beans.factory.annotation.Autowired;
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
                                     @RequestParam(required = false) String city){
        //Budapest;
        var lat = "47.497913";
        var lng = "19.040236";
        return openSunService.getWeatherResponseForCityByDate(date, lat, lng);
    }
}
