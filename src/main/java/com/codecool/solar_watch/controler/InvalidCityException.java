package com.codecool.solar_watch.controler;

public class InvalidCityException extends RuntimeException{
   public InvalidCityException(){
       super("Invalid City name or City is not found");
   }
}
