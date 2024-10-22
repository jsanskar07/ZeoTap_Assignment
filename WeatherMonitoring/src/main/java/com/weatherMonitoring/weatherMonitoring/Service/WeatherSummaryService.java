package com.weatherMonitoring.weatherMonitoring.Service;

import com.weatherMonitoring.weatherMonitoring.Entity.DailyWeatherSummary;
import com.weatherMonitoring.weatherMonitoring.Entity.WeatherEntity;
import com.weatherMonitoring.weatherMonitoring.Repository.DailySummaryRepository;
import com.weatherMonitoring.weatherMonitoring.Repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.*;

@Service
public class WeatherSummaryService {

    @Autowired
    private WeatherRepository weatherRepository;

    @Autowired
    private DailySummaryRepository dailySummaryRepository;

    @Scheduled(cron = "0 0 0 * * ?") // Run every day at midnight
    public void generateDailySummaries() {
        Date today = new Date();
        Date startOfDay = getStartOfDay(today);
        Date endOfDay = getEndOfDay(today);
        List<String> cities = Arrays.asList("Delhi", "Mumbai", "Chennai", "Bangalore", "Kolkata", "Hyderabad");
        for (String city : cities) {
            List<WeatherEntity> weatherData = weatherRepository.findByCityAndTimestampBetween(city, startOfDay, endOfDay);

            double avgTemp = weatherData.stream().mapToDouble(WeatherEntity::getTemp).average().orElse(Double.NaN);
            double maxTemp = weatherData.stream().mapToDouble(WeatherEntity::getTemp).max().orElse(Double.NaN);
            double minTemp = weatherData.stream().mapToDouble(WeatherEntity::getTemp).min().orElse(Double.NaN);

            String dominantCondition = weatherData.stream()
                    .collect(Collectors.groupingBy(WeatherEntity::getWeatherCondition, Collectors.counting()))
                    .entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();

            DailyWeatherSummary summary = new DailyWeatherSummary();
            summary.setCity(city);
            summary.setAvgTemp(avgTemp);
            summary.setMaxTemp(maxTemp);
            summary.setMinTemp(minTemp);
            summary.setDominantCondition(dominantCondition);
            summary.setDate(today);

            dailySummaryRepository.save(summary);
        }
    }

    // Helper functions to get start and end of the day
    private Date getStartOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    private Date getEndOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }
}
