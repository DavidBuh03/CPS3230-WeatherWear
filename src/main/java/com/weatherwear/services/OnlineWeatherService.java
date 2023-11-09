package com.weatherwear.services;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class OnlineWeatherService implements WeatherService {
    @Override
    public JsonObject requestWeatherNow(int service, String ip) {
        try {
            Gson gson = new Gson();
            JsonObject serialized;
            switch(service) {
                case 1: {
                    HttpRequest request = HttpRequest.newBuilder()
                            .uri(URI.create("https://weatherapi-com.p.rapidapi.com/current.json?q=" + ip))
                            .header("X-RapidAPI-Key", "882596b6femsh0251b0f1ac25755p14a93ajsn116021aec172")
                            .header("X-RapidAPI-Host", "weatherapi-com.p.rapidapi.com")
                            .timeout(Duration.ofSeconds(3))
                            .method("GET", HttpRequest.BodyPublishers.noBody())
                            .build();
                    HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
                    serialized = gson.fromJson(response.body(), JsonObject.class);
                    return serialized;
                }
                case 2: {
                    JsonObject location;
                    String country;
                    HttpRequest request1 = HttpRequest.newBuilder()
                            .uri(URI.create("http://ip-api.com/json/" + ip))
                            .timeout(Duration.ofSeconds(3))
                            .method("GET", HttpRequest.BodyPublishers.noBody())
                            .build();
                    HttpResponse<String> response1 = HttpClient.newHttpClient().send(request1, HttpResponse.BodyHandlers.ofString());
                    location = gson.fromJson(response1.body(), JsonObject.class);
                    country = location.getAsJsonObject("members").getAsJsonPrimitive("country").getAsString();

                    HttpRequest request2 = HttpRequest.newBuilder()
                            .uri(URI.create("https://weatherapi-com.p.rapidapi.com/current.json?q=" + country))
                            .header("X-RapidAPI-Key", "882596b6femsh0251b0f1ac25755p14a93ajsn116021aec172")
                            .header("X-RapidAPI-Host", "weatherapi-com.p.rapidapi.com")
                            .timeout(Duration.ofSeconds(3))
                            .method("GET", HttpRequest.BodyPublishers.noBody())
                            .build();
                    HttpResponse<String> response2 = HttpClient.newHttpClient().send(request2, HttpResponse.BodyHandlers.ofString());
                    serialized = gson.fromJson(response2.body(), JsonObject.class);
                    return serialized;
                }
                default: {
                    System.out.println("An invalid service has been chosen.");
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public JsonObject requestWeatherForecast(String date, String iata) {
        try {
            Gson gson = new Gson();
            JsonObject serialized;
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://weatherapi-com.p.rapidapi.com/forecast.json?q=iata%3A" + iata + "&days=3&dt=" + date))
                    .header("X-RapidAPI-Key", "882596b6femsh0251b0f1ac25755p14a93ajsn116021aec172")
                    .header("X-RapidAPI-Host", "weatherapi-com.p.rapidapi.com")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            serialized = gson.fromJson(response.body(), JsonObject.class);
            return serialized;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
