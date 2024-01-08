package com.codecool.solar_watch.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SunTimeApiResults(SunTimeReport results) {

}
