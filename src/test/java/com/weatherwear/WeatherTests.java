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
    public void testRecommendationColdWeatherNoRain() {
        //Setup
        WeatherResult testResult = new WeatherResult(TEMPERATURE_LESS_THAN_FIFTEEN_DEGREES, PRECIPITATION_ZERO);
        Recommendation recommendation;
        //Exercise
        recommendation = weather.recommend(testResult);
        //Verify
        Assertions.assertTrue(recommendation.clothingCold);
        Assertions.assertFalse(recommendation.umbrella);
        //Teardown

    }
    @Test
    public void testRecommendationColdWeatherFifteenDegreesNoRain() {
        //Setup
        WeatherResult testResult = new WeatherResult(TEMPERATURE_FIFTEEN_DEGREES, PRECIPITATION_ZERO);
        Recommendation recommendation;
        //Exercise
        recommendation = weather.recommend(testResult);
        //Verify
        Assertions.assertTrue(recommendation.clothingCold);
        Assertions.assertFalse(recommendation.umbrella);
        //Teardown

    }
    @Test
    public void testRecommendationWarmWeatherNoRain() {
        //Setup
        WeatherResult testResult = new WeatherResult(TEMPERATURE_HIGHER_THAN_FIFTEEN_DEGREES, PRECIPITATION_ZERO);
        Recommendation recommendation;
        //Exercise
        recommendation = weather.recommend(testResult);
        //Verify
        Assertions.assertFalse(recommendation.clothingCold);
        Assertions.assertFalse(recommendation.umbrella);
        //Teardown

    }
    @Test
    public void testRecommendationColdWeatherWithRain() {
        //Setup
        WeatherResult testResult = new WeatherResult(TEMPERATURE_LESS_THAN_FIFTEEN_DEGREES, PRECIPITATION_NONZERO);
        Recommendation recommendation;
        //Exercise
        recommendation = weather.recommend(testResult);
        //Verify
        Assertions.assertTrue(recommendation.clothingCold);
        Assertions.assertTrue(recommendation.umbrella);
        //Teardown

    }
    @Test
    public void testRecommendationColdWeatherFifteenDegreesWithRain() {
        //Setup
        WeatherResult testResult = new WeatherResult(TEMPERATURE_FIFTEEN_DEGREES, PRECIPITATION_NONZERO);
        Recommendation recommendation;
        //Exercise
        recommendation = weather.recommend(testResult);
        //Verify
        Assertions.assertTrue(recommendation.clothingCold);
        Assertions.assertTrue(recommendation.umbrella);
        //Teardown

    }
    @Test
    public void testRecommendationWarmWeatherWithRain() {
        //Setup
        WeatherResult testResult = new WeatherResult(TEMPERATURE_HIGHER_THAN_FIFTEEN_DEGREES, PRECIPITATION_NONZERO);
        Recommendation recommendation;
        //Exercise
        recommendation = weather.recommend(testResult);
        //Verify
        Assertions.assertFalse(recommendation.clothingCold);
        Assertions.assertTrue(recommendation.umbrella);
        //Teardown

    }





























































    /*
    @Test
    public void testWeatherLessThanFifteenDegrees() throws IOException, InterruptedException {
        //setup
        WeatherService weatherService = Mockito.mock(WeatherService.class);
        Mockito.when(weatherService.requestWeather()).thenReturn(new WeatherResult(TEMPERATURE_LESS_THAN_FIFTEEN_DEGREES, 0));
        //exercise
        boolean result = weather.recommendColdClothing(weatherService);
        //verify
        Assertions.assertTrue(result);
        //teardown
    }
    @Test
    public void testWeatherAtFifteenDegrees() throws IOException, InterruptedException {
        //setup
        WeatherService weatherService = Mockito.mock(WeatherService.class);
        Mockito.when(weatherService.requestWeather()).thenReturn(new WeatherResult(TEMPERATURE_FIFTEEN_DEGREES, 0));
        //exercise
        boolean result = weather.recommendColdClothing(weatherService);
        //verify
        Assertions.assertTrue(result);
        //teardown
    }
    @Test
    public void testWeatherHigherThanFifteenDegrees() throws IOException, InterruptedException {
        //setup
        WeatherService weatherService = Mockito.mock(WeatherService.class);
        Mockito.when(weatherService.requestWeather()).thenReturn(new WeatherResult(TEMPERATURE_HIGHER_THAN_FIFTEEN_DEGREES, 0));
        //exercise
        boolean result = weather.recommendColdClothing(weatherService);
        //verify
        Assertions.assertFalse(result);
        //teardown
    }

    @Test
    public void testWeatherIsRaining() throws IOException, InterruptedException {
        //setup
        WeatherService weatherService = Mockito.mock(WeatherService.class);
        Mockito.when(weatherService.requestWeather()).thenReturn(new WeatherResult(TEMPERATURE_FIFTEEN_DEGREES, 1));
        //exercise
        boolean result = weather.recommendUmbrella(weatherService);
        //verify
        Assertions.assertTrue(result);
        //teardown
    }
    @Test
    public void testWeatherIsNotRaining() throws IOException, InterruptedException {
        //setup
        WeatherService weatherService = Mockito.mock(WeatherService.class);
        Mockito.when(weatherService.requestWeather()).thenReturn(new WeatherResult(TEMPERATURE_FIFTEEN_DEGREES, 0));
        //exercise
        boolean result = weather.recommendUmbrella(weatherService);
        //verify
        Assertions.assertFalse(result);
        //teardown
    }
    @Test
    public void testWeatherServiceNull() throws IOException, InterruptedException {
        //setup
        WeatherService weatherService = Mockito.mock(WeatherService.class);
        Mockito.when(weatherService.requestWeather()).thenReturn(new WeatherResult());
        //exercise
        boolean result = weather.recommendUmbrella(weatherService);
        //verify
        Assertions.assertFalse(result);
        //teardown
    }

    @Test
    public void testOverriddenWeatherServiceOutput() throws IOException, InterruptedException {
        //setup
        Weather mockWeather = Mockito.mock(Weather.class);
        Mockito.when(mockWeather.requestWeather()).thenReturn(new WeatherResult(15, 1));
        //exercise
        WeatherResult testResult = mockWeather.requestWeather();
        //verify
        Assertions.assertEquals(15, testResult.temperature);
        //Assertions.assertEquals(testResult.);

        //teardown
    }
    */

}
