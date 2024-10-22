package com.weatherMonitoring.weatherMonitoring.Controller;

import com.weatherMonitoring.weatherMonitoring.DTO.WeatherResponse;
import com.weatherMonitoring.weatherMonitoring.DTO.WeatherResponseApi;
import com.weatherMonitoring.weatherMonitoring.Entity.WeatherEntity;
import com.weatherMonitoring.weatherMonitoring.Service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/allCities")
    public WeatherResponseApi getWeather(){
        return weatherService.getWeather();
    }

    @GetMapping("/getAlert")
    public void notification(@RequestParam("email") String email, @RequestParam("city") String city, @RequestParam("temp") float temp){
        return weatherService.
    }
    // Endpoint to get weather data for a specific city
    @GetMapping("/{city}")
    public String getWeatherForCity(@PathVariable String city) {
        WeatherResponse weatherResponse = weatherService.fetchWeather(city);

        // Converting Kelvin to Celsius
        double tempInCelsius = weatherResponse.getMain().getTemp() - 273.15;
        double feelsLikeInCelsius = weatherResponse.getMain().getFeels_like() - 273.15;

        // Building a simple response
        return String.format(
                "City: %s\nTemperature: %.2f°C\nFeels Like: %.2f°C\nWeather: %s\nTimestamp: %d",
                city,
                tempInCelsius,
                feelsLikeInCelsius,
                weatherResponse.getWeather().get(0).getMain(),
                weatherResponse.getDt()
        );
    }
}
