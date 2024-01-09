package com.codecool.solar_watch.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GeoParser(ResultOfGEoParser resultOfGEoParser) {
}
