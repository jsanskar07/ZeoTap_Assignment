package com.weatherMonitoring.weatherMonitoring.DTO;

import lombok.Data;

import java.util.List;

@Data
public class WeatherResponse {
    private List<Weather> weather;
    private Main main;
    private long dt; // Unix timestamp

    @Data
    public static class Weather {
        private String main;

        public String getMain() {
            return main;
        }

        public void setMain(String main) {
            this.main = main;
        }
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    @Data
    public static class Main {
        private double temp;
        private double feels_like;

        public double getTemp() {
            return temp;
        }

        public void setTemp(double temp) {
            this.temp = temp;
        }

        public double getFeels_like() {
            return feels_like;
        }

        public void setFeels_like(double feels_like) {
            this.feels_like = feels_like;
        }
    }
}
