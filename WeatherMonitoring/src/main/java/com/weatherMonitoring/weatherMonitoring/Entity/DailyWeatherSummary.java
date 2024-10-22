package com.weatherMonitoring.weatherMonitoring.Entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "daily_weather_summary")
public class DailyWeatherSummary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;
    private double avgTemp;
    private double maxTemp;
    private double minTemp;
    private String dominantCondition;
    private Date date;

    // Getters and Setters

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getAvgTemp() {
        return avgTemp;
    }

    public void setAvgTemp(double avgTemp) {
        this.avgTemp = avgTemp;
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(double maxTemp) {
        this.maxTemp = maxTemp;
    }

    public double getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(double minTemp) {
        this.minTemp = minTemp;
    }

    public String getDominantCondition() {
        return dominantCondition;
    }

    public void setDominantCondition(String dominantCondition) {
        this.dominantCondition = dominantCondition;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

