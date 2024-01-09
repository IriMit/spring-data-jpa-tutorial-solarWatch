package com.codecool.solar_watch.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public record ResultOfGEoParser(double lat, double lon) {
}
