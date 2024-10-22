package com.weatherMonitoring.weatherMonitoring.DTO;


import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class WeatherResponseApi {
    List<Weather> data;
//    List<String> alerts;
    public WeatherResponseApi(){
        data = new ArrayList<>();
//        alerts = new ArrayList<>();
    }

    public List<Weather> getData() {
        return data;
    }

    public void addData(Weather data) {
        this.data.add(data);
    }

//    public List<String> getAlerts() {
//        return alerts;
//    }

//    public void addAlerts(String alert) {
//        this.alerts.add(alert);
//    }

    public static class Weather{
        private String city;
        private String temp;
        private String feelsLike;
        private String weatherCondition;
        private Date date;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getTemp() {
            return temp;
        }

        public void setTemp(double temp) {
            String result = String.format("%.2f", temp);
            result += " \u2103";
            this.temp = result;
        }

        public String getFeelsLike() {
            return feelsLike;
        }

        public void setFeelsLike(double feelsLike) {
            String result = String.format("%.2f", feelsLike);
            result += " \u2103";
            this.feelsLike = result;
        }

        public String getWeatherCondition() {
            return weatherCondition;
        }

        public void setWeatherCondition(String weatherCondition) {
            this.weatherCondition = weatherCondition;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }
    }
}
