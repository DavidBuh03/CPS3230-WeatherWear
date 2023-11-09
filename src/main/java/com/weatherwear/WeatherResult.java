package com.weatherwear;

public class WeatherResult {

    public String country;
    public String name;
    public float temperature;
    public float precipitation;



    public WeatherResult(String country, String name, float temperature, float precipitation) {
        this.country = country;
        this.name = name;
        this.temperature = temperature;
        this.precipitation = precipitation;
    }
    public WeatherResult(float temperature, float precipitation) {
        this.temperature = temperature;
        this.precipitation = precipitation;
    }
}
