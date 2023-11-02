package com.weatherwear;

import com.weatherwear.services.WeatherService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.function.Try;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class WeatherTests {
    WeatherService weatherService;
    Weather weather;

    final int TEMPERATURE_LESS_THAN_FIFTEEN_DEGREES = 4;
    final int TEMPERATURE_FIFTEEN_DEGREES = 15;
    final int TEMPERATURE_HIGHER_THAN_FIFTEEN_DEGREES = 16;

    @BeforeEach
    public void setup() {
        weather = new Weather();
    }

    @Test
    public void testWeatherLessThanFifteenDegrees() {
        //setup
        WeatherService weatherService = Mockito.mock(WeatherService.class);
        Mockito.when(weatherService.getWeather()).thenReturn(new WeatherResult(TEMPERATURE_LESS_THAN_FIFTEEN_DEGREES, false));
        //exercise
        boolean result = weather.recommendColdClothing(weatherService);
        //verify
        Assertions.assertTrue(result);
        //teardown
    }
    @Test
    public void testWeatherAtFifteenDegrees() {
        //setup
        WeatherService weatherService = Mockito.mock(WeatherService.class);
        Mockito.when(weatherService.getWeather()).thenReturn(new WeatherResult(TEMPERATURE_FIFTEEN_DEGREES, false));
        //exercise
        boolean result = weather.recommendColdClothing(weatherService);
        //verify
        Assertions.assertTrue(result);
        //teardown
    }
    @Test
    public void testWeatherHigherThanFifteenDegrees() {
        //setup
        WeatherService weatherService = Mockito.mock(WeatherService.class);
        Mockito.when(weatherService.getWeather()).thenReturn(new WeatherResult(TEMPERATURE_HIGHER_THAN_FIFTEEN_DEGREES, false));
        //exercise
        boolean result = weather.recommendColdClothing(weatherService);
        //verify
        Assertions.assertFalse(result);
        //teardown
    }

    @Test
    public void testWeatherIsRaining() {
        //setup
        WeatherService weatherService = Mockito.mock(WeatherService.class);
        Mockito.when(weatherService.getWeather()).thenReturn(new WeatherResult(TEMPERATURE_FIFTEEN_DEGREES, true));
        //exercise
        boolean result = weather.recommendUmbrella(weatherService);
        //verify
        Assertions.assertTrue(result);
        //teardown
    }
    @Test
    public void testWeatherIsNotRaining() {
        //setup
        WeatherService weatherService = Mockito.mock(WeatherService.class);
        Mockito.when(weatherService.getWeather()).thenReturn(new WeatherResult(TEMPERATURE_FIFTEEN_DEGREES, false));
        //exercise
        boolean result = weather.recommendUmbrella(weatherService);
        //verify
        Assertions.assertFalse(result);
        //teardown
    }
    @Test
    public void testWeatherServiceNull() {
        //setup
        WeatherService weatherService = Mockito.mock(WeatherService.class);
        Mockito.when(weatherService.getWeather()).thenAnswer(new Answer<WeatherResult>() {
            @Override
            public WeatherResult answer(InvocationOnMock invocation) throws InterruptedException {
                Thread.sleep(5000);
                return new WeatherResult(TEMPERATURE_FIFTEEN_DEGREES, false);
            }
        }) ;
        //exercise
        boolean result = weather.recommendUmbrella(weatherService);
        //verify
        Assertions.assertFalse(result);
        //teardown
    }
}
