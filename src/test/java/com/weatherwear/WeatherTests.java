package com.weatherwear;

import com.weatherwear.stubs.IpServiceStubs;
import com.weatherwear.stubs.WeatherServiceStubs;
import org.junit.jupiter.api.BeforeEach;


public class WeatherTests {
    Weather weather;

    //final int TEMPERATURE_LESS_THAN_FIFTEEN_DEGREES = 4;
    //final int TEMPERATURE_FIFTEEN_DEGREES = 15;
    //final int TEMPERATURE_HIGHER_THAN_FIFTEEN_DEGREES = 16;

    @BeforeEach
    public void setup() {
        weather = new Weather();
        weather.setIpService(new IpServiceStubs());
        weather.setWeatherService(new WeatherServiceStubs());
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
