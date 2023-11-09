package com.weatherwear.stubs;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.weatherwear.services.WeatherService;

public class WeatherServiceStubs implements WeatherService {
    //these stubs also work as spies because i can only inject one dependency per interface
    public int requestWeatherNowCallCount = 0;
    public int requestWeatherForecastCallCount = 0;
    String weatherNowSampleOutput = "{\"location\":{\"name\":\"Tarxien\",\"region\":\"\",\"country\":\"Malta\"},\"current\":{\"temp_c\":21.0,\"precip_mm\":0.05}}";
    String weatherForecastSampleOutput = "{\"location\":{\"name\":\"Luqa\",\"region\":\"\",\"country\":\"Malta\"},\"forecast\":{\"forecastday\":[{\"day\":{\"avgtemp_c\":20.8,\"totalprecip_mm\":2.62}}]}}";
    Gson gson = new Gson();
    @Override
    public JsonObject requestWeatherNow(int service, String ip) {
        requestWeatherNowCallCount++;
        JsonObject example = gson.fromJson(weatherNowSampleOutput, JsonObject.class);
        return example;
    }
    @Override
    public JsonObject requestWeatherForecast(String date, String iata) {
        requestWeatherForecastCallCount++;
        JsonObject example = gson.fromJson(weatherForecastSampleOutput, JsonObject.class);
        return example;
    }

}
