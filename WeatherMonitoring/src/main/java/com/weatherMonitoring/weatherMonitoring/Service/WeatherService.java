package com.weatherMonitoring.weatherMonitoring.Service;

import com.weatherMonitoring.weatherMonitoring.DTO.WeatherResponse;
import com.weatherMonitoring.weatherMonitoring.DTO.WeatherResponseApi;
import com.weatherMonitoring.weatherMonitoring.Email.EmailService;
import com.weatherMonitoring.weatherMonitoring.Entity.WeatherEntity;
import com.weatherMonitoring.weatherMonitoring.Repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;


import java.util.*;

@Service
public class WeatherService {

    private static final String API_KEY = "5f66bb83ffae84c2fba2e2ba6bb6e872";
    private static final String WEATHER_URL = "https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WeatherRepository weatherRepository;

    @Autowired
    private EmailService emailService;
    // Define the list of cities
    private List<String> cities = Arrays.asList("Delhi", "Mumbai", "Chennai", "Bangalore", "Kolkata", "Hyderabad");

    private Map<String, String> userEmais = new HashMap<>();
    public WeatherResponseApi getWeather(){
        WeatherResponseApi response = new WeatherResponseApi();
        List<WeatherEntity> dd = weatherRepository.findAll();

        if(dd.size() == 0) fetchWeatherData();
        dd = weatherRepository.findAll();
        for(WeatherEntity x : dd){
            WeatherResponseApi.Weather weather = new WeatherResponseApi.Weather();
            weather.setCity(x.getCity());
            weather.setWeatherCondition(x.getWeatherCondition());
            weather.setTemp(x.getTemp());
            weather.setFeelsLike(x.getFeelsLike());
            weather.setDate(x.getTimestamp());
            response.addData(weather);
        }


        return response;
    }
    // Fetch weather data for all cities
    @Scheduled(fixedRate = 100000) // 5 minutes
    public void fetchWeatherData() {
        System.out.println("Open Weather API is called");
        weatherRepository.deleteAll();
        for (String city : cities) {
            String url = String.format(WEATHER_URL, city, API_KEY);
            WeatherResponse response = restTemplate.getForObject(url, WeatherResponse.class);
            System.out.println(response.getWeather().get(0));
            if (response != null) {
                processWeatherData(city, response);
            }

        }
    }

    // Fetch weather data for a specific city
    public WeatherResponse fetchWeather(String city) {
        String url = String.format(WEATHER_URL, city, API_KEY);
        return restTemplate.getForObject(url, WeatherResponse.class);
    }

    // Process the weather data and store rollups
    private void processWeatherData(String city, WeatherResponse response) {
        double tempCelsius = response.getMain().getTemp() - 273.15;
        double feelsLikeCelsius = response.getMain().getFeels_like() - 273.15;

        WeatherEntity entity = new WeatherEntity();
        entity.setCity(city);
        entity.setTemp(tempCelsius);
        entity.setFeelsLike(feelsLikeCelsius);
        entity.setWeatherCondition(response.getWeather().get(0).getMain());
        entity.setTimestamp(new Date(response.getDt() * 1000L)); // Convert Unix to Date
        weatherRepository.save(entity);
        //checkForAlerts(city, tempCelsius);
    }

    // Check for threshold breaches
    private void checkForAlerts(String city, double tempCelsius) {

        if (tempCelsius > 28) {
            // Trigger alert
            String temp = String.format("%.2f", tempCelsius);
            String message = "ALERT: High temperature in " + city + "! Current temp: " + temp + "℃";
            String subject = "Subject: Weather Alert: High Temperature in" + city;
            emailService.sendEmail("jsanskar007@gmail.com",subject, message);
            System.out.println("Email sent");
        }
    }
}
