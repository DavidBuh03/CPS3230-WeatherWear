package com.weatherwear.services;
import com.google.gson.JsonObject;

public interface WeatherService {

    JsonObject requestWeatherNow(int service, String ip);
    JsonObject requestWeatherForecast(String date, String iata);
}
