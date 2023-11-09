package com.weatherwear;
import com.weatherwear.services.OnlineIpService;
import com.weatherwear.services.OnlineWeatherService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class WeatherWearApp {

    public static void main (String args[]) {

        Scanner s = new Scanner(System.in);
        s.useDelimiter("\n");
        Weather weather = new Weather();
        weather.setIpService(new OnlineIpService());
        weather.setWeatherService(new OnlineWeatherService());
        int userInput = 0;
        do {
            try {
                System.out.println(
                        "WeatherWear.com\n" +
                        "---------------\n" +
                        "1. Recommend clothing for current location\n" +
                        "2. Recommend clothing for future location\n" +
                        "3. Exit\n\n" +
                        "Enter choice: ");
                userInput = s.nextInt();
                s.nextLine();

                switch(userInput) {
                    case 1: {
                        weather.recommend(weather.getWeatherNow());
                        System.out.println("Weather Now:");
                        printResult(weather.lastWeatherResult, weather.lastRecommendation);
                    } break;
                    case 2: {
                        boolean validDate = false;
                        String date;
                        Date validateDate;
                        do {
                            System.out.println(
                                    "Enter a forecast date.\n" +
                                            "(Not more than 10 days in the future)\n" +
                                            "(Required format: YYYY/MM/dd) ");
                           date = s.nextLine();
                           try {
                               validateDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
                               long difference = TimeUnit.MILLISECONDS.toDays(validateDate.getTime() - new Date().getTime());
                               if (difference < 0) {
                                   validDate = false;
                                   System.out.println("Invalid date: cannot forecast a past date.");
                               }
                               else if (difference > 10) {
                                   validDate = false;
                                   System.out.println("Date cannot be more than 10 days into the future.");
                               }
                               else {
                                    validDate = true;
                               }
                           } catch (Exception e) {
                               System.out.println("Date was invalid.");
                               validDate = false;
                           }
                        } while (!validDate);

                        System.out.println(
                                "Enter an airport IATA code.\n" +
                                        "(E.g. MLA = Malta International Airport) ");
                        String iata = s.nextLine();
                        weather.recommend(weather.getWeatherForecast(date, iata));
                        
                        
                        weather.getWeatherForecast(date, iata);
                        weather.recommend(weather.lastWeatherResult);
                        System.out.println("Weather forecast for " + date + ":");
                        printResult(weather.lastWeatherResult, weather.lastRecommendation);
                    }
                    case 3: break;
                    default: System.out.println("Invalid input. Please try again.");
                }
            if (userInput == 3) System.out.println("App exited.");

            } catch (InputMismatchException invalid) {
                System.out.println("Invalid input. Please try again.");
                s.nextLine();
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (userInput!=3);
    }
    public static void printResult(WeatherResult result, Recommendation recommendation) {
        System.out.println("Weather in " + result.name + ", " + result.country + ": ");
        System.out.println("Temperature: " + result.temperature + "C, Precipitation: " + result.precipitation + "mm.");
        System.out.println("-------------");
        System.out.println("It is " + (recommendation.clothingCold? "cold" : "warm") + " so you should wear " + (recommendation.clothingCold? "warm" : "light") + " clothing.");
        System.out.println("It is " + (recommendation.umbrella? "currently" : "not") + " raining so you " + (recommendation.umbrella? "do" : "don't") + " need an umbrella.");
        System.out.println("-------------\n");
    }
    
}
