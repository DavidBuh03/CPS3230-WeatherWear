package com.weatherwear;

import com.weatherwear.services.GeolocationService;
import com.weatherwear.services.WeatherService;

import java.nio.file.Watchable;
import java.util.Date;

public class Weather {
    protected WeatherService weatherService;
    protected GeolocationService geolocationService;
    protected Date date;

    public void setWeatherService (WeatherService weatherService) {
        this.weatherService = weatherService;
    }
    public void setGeolocationService (GeolocationService geolocationService) {
        this.geolocationService = geolocationService;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public boolean recommendColdClothing(WeatherService weatherService) {
        WeatherResult result = weatherService.getWeather();
        if (result.temperature <= 15) return true;
        else return false;
    }
    public boolean recommendUmbrella(WeatherService weatherService) {
        WeatherResult result = weatherService.getWeather();
        return result.isRaining;
    }

}
