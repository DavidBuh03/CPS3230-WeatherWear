package com.weatherwear;

import com.weatherwear.stubs.IpServiceStubs;
import com.weatherwear.stubs.WeatherServiceStubs;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class WeatherTests {
    Weather weather;
    IpServiceStubs ipService;
    WeatherServiceStubs weatherService;

    final float TEMPERATURE_LESS_THAN_FIFTEEN_DEGREES = 4;
    final float TEMPERATURE_FIFTEEN_DEGREES = 15;
    final float TEMPERATURE_HIGHER_THAN_FIFTEEN_DEGREES = 15.1F;
    final float PRECIPITATION_ZERO = 0;
    final float PRECIPITATION_NONZERO = 0.1F;

    @BeforeEach
    public void setup() {
        weather = new Weather();
        ipService = new IpServiceStubs();
        weatherService = new WeatherServiceStubs();
        weather.setIpService(ipService);
        weather.setWeatherService(weatherService);
    }

    @Test
    public void testConvertingWeatherNowJsonToWeatherResultObject() {
        //Setup
        WeatherResult testResult;
        //Exercise
        testResult = weather.convertWeatherNowToWeatherResult(weather.weatherService.requestWeatherNow(1, "test"));
        //Verify
        Assertions.assertNotNull(testResult);
        //Teardown
    }

    @Test
    public void testConvertingWeatherForecastJsonToWeatherResultObject() {
        //Setup
        WeatherResult testResult;
        //Exercise
        testResult = weather.convertWeatherForecastToWeatherResult(weather.weatherService.requestWeatherForecast("test", "test"));
        //Verify
        Assertions.assertNotNull(testResult);
        //Teardown
    }

    @Test
    public void testGetWeatherNow() {
        //Setup
        WeatherResult testResult;
        //Exercise
        testResult = weather.getWeatherNow();
        //Verify
        Assertions.assertNotNull(testResult);

        Assertions.assertEquals(1, ipService.getIpAddressCallCount);
        Assertions.assertEquals(1, weatherService.requestWeatherNowCallCount);
        //Teardown
    }

    @Test
    public void testGetWeatherForecast() {
        //Setup
        WeatherResult testResult;
        //Exercise
        testResult = weather.getWeatherForecast("test", "test");
        //Verify
        Assertions.assertNotNull(testResult);
        Assertions.assertEquals(1, weatherService.requestWeatherForecastCallCount);
        //Teardown
    }

    @Test
    public void testRecommendColdClothingLessThanFifteenDegrees() {
        //Setup
        WeatherResult testResult = new WeatherResult(TEMPERATURE_LESS_THAN_FIFTEEN_DEGREES, PRECIPITATION_ZERO);
        //Exercise
        boolean result = weather.recommendColdClothing(testResult);
        //Verify
        Assertions.assertTrue(result);
        //Teardown
    }
    @Test
    public void testRecommendColdClothingFifteenDegrees() {
        //Setup
        WeatherResult testResult = new WeatherResult(TEMPERATURE_FIFTEEN_DEGREES, PRECIPITATION_ZERO);
        //Exercise
        boolean result = weather.recommendColdClothing(testResult);
        //Verify
        Assertions.assertTrue(result);
        //Teardown
    }
    @Test
    public void testRecommendColdClothingHigherThanFifteenDegrees() {
        //Setup
        WeatherResult testResult = new WeatherResult(TEMPERATURE_HIGHER_THAN_FIFTEEN_DEGREES, PRECIPITATION_ZERO);
        //Exercise
        boolean result = weather.recommendColdClothing(testResult);
        //Verify
        Assertions.assertFalse(result);
        //Teardown
    }
    @Test
    public void testRecommendUmbrellaWhenNotRaining() {
        //Setup
        WeatherResult testResult = new WeatherResult(TEMPERATURE_FIFTEEN_DEGREES, PRECIPITATION_ZERO);
        //Exercise
        boolean result = weather.recommendUmbrella(testResult);
        //Verify
        Assertions.assertFalse(result);
        //Teardown
    }
    @Test
    public void testRecommendUmbrellaWhenRaining() {
        //Setup
        WeatherResult testResult = new WeatherResult(TEMPERATURE_FIFTEEN_DEGREES, PRECIPITATION_NONZERO);
        //Exercise
        boolean result = weather.recommendUmbrella(testResult);
        //Verify
        Assertions.assertTrue(result);
        //Teardown
    }

    @Test
    public void testRecommendation() {
        //Setup
        WeatherResult testResult = new WeatherResult(TEMPERATURE_FIFTEEN_DEGREES, PRECIPITATION_ZERO);
        Recommendation recommendation;
        //Exercise
        boolean coldResult = weather.recommendColdClothing(testResult);
        boolean umbrellaResult = weather.recommendUmbrella(testResult);
        recommendation = weather.recommend(testResult);
        //Verify
        Assertions.assertEquals(coldResult, recommendation.clothingCold);
        Assertions.assertEquals(umbrellaResult, recommendation.umbrella);
        //Teardown
    }
}

