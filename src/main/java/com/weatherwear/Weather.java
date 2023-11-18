package com.weatherwear;
import com.google.gson.JsonObject;
import com.weatherwear.services.IpService;
import com.weatherwear.services.WeatherService;
public class Weather {

    protected WeatherResult lastWeatherResult;
    protected Recommendation lastRecommendation;
    protected String currentIp;

    protected IpService ipService;

    protected WeatherService weatherService;

    public void setWeatherService(WeatherService weatherService) { this.weatherService = weatherService; }
    public void setIpService(IpService ipService) { this.ipService = ipService; }

    public Weather(){}

    public WeatherResult getWeatherNow() {
        currentIp = ipService.getIpAddress();
        JsonObject result;
        for (int i = 1; i <=2; i++) {
            result = weatherService.requestWeatherNow(i, currentIp);
            if (result!=null) {
                lastWeatherResult = convertWeatherNowToWeatherResult(result);
                break;
            }
        }
        return lastWeatherResult;
    }

    public WeatherResult getWeatherForecast(String date, String iata) {
        JsonObject result;
        result = weatherService.requestWeatherForecast(date, iata);
        lastWeatherResult = convertWeatherForecastToWeatherResult(result);
        return lastWeatherResult;
    }

    public WeatherResult convertWeatherNowToWeatherResult(JsonObject weatherResult) {
        return new WeatherResult(
            weatherResult
                .getAsJsonObject("location")
                .getAsJsonPrimitive("country")
                .getAsString(),

            weatherResult
                .getAsJsonObject("location")
                .getAsJsonPrimitive("name")
                .getAsString(),

            weatherResult
                .getAsJsonObject("current")
                .getAsJsonPrimitive("temp_c")
                .getAsFloat(),

            weatherResult
                .getAsJsonObject("current")
                .getAsJsonPrimitive("precip_mm")
                .getAsFloat()
        );
    }

    public WeatherResult convertWeatherForecastToWeatherResult(JsonObject weatherResult) {
        return new WeatherResult(
                weatherResult
                    .getAsJsonObject("location")
                    .getAsJsonPrimitive("country")
                    .getAsString(),

                weatherResult
                    .getAsJsonObject("location")
                    .getAsJsonPrimitive("name")
                    .getAsString(),

                weatherResult
                    .getAsJsonObject("forecast")
                    .getAsJsonArray("forecastday")
                    .get(0)
                    .getAsJsonObject()
                    .getAsJsonObject("day")
                    .getAsJsonPrimitive("avgtemp_c")
                    .getAsFloat(),

                weatherResult
                    .getAsJsonObject("forecast")
                    .getAsJsonArray("forecastday")
                    .get(0)
                    .getAsJsonObject()
                    .getAsJsonObject("day")
                    .getAsJsonPrimitive("totalprecip_mm")
                    .getAsFloat()
        );
    }

    public boolean recommendColdClothing(WeatherResult weatherResult) {
        return (weatherResult.temperature<=15);
    }
    public boolean recommendUmbrella(WeatherResult weatherResult) {
        return (weatherResult.precipitation>0);
    }

    public Recommendation recommend(WeatherResult weatherResult) {
        Recommendation recommendation = new Recommendation(recommendColdClothing(weatherResult), recommendUmbrella((weatherResult)));
        lastRecommendation = recommendation;
        return recommendation;
    }
}
