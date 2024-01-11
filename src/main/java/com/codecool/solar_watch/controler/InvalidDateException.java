package com.codecool.solar_watch.controler;

public class InvalidDateException extends RuntimeException {
    public InvalidDateException(){
        super("Date is required");
    }
}
