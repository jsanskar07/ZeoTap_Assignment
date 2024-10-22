# Real-Time Weather Monitoring System

Overview

This project is a real-time weather monitoring system that continuously retrieves weather data from the OpenWeatherMap API and provides summarized insights through rollups and aggregates. It supports alerting for specific weather conditions, visualizations, and. Email alerts are triggered when weather thresholds are exceeded.

Features

	•	Fetches weather data in real time for Indian metro cities (e.g., Delhi, Mumbai, Chennai, etc.).
	•	Performs temperature conversions from Kelvin to Celsius.
	•	Aggregates daily weather data to calculate:
	•	Average temperature.
	•	Maximum temperature.
	•	Minimum temperature.
	•	Dominant weather condition.
	•	Alert system for temperature or weather condition thresholds.
	•	Redis integration for caching weather data.
	•	Email notification system using JavaMailSender.
	•	Extensible for additional weather parameters and forecasts.
	•	Visualizations for weather summaries and trends.

Technologies Used

	•	Java 17: The core programming language for the application.
	•	Spring Boot: Simplifies application development and integrates various features like REST APIs and scheduling.
	•	Redis: In-memory caching for weather data.
	•	Spring Data Redis: Simplifies interaction with Redis.
	•	JavaMailSender: Email alert system.
	•	RestTemplate: For making API requests to the OpenWeatherMap API.
	•	Thymeleaf (Optional): For rendering visualizations and reports.
	•	Maven: For dependency management.

API Used

	•	OpenWeatherMap API: Real-time weather data.

Prerequisites

To run this project, you need:

	•	Java 17 or higher.
	•	Maven (for dependency management).
	•	A Redis server running locally or remotely.
	•	An API key from OpenWeatherMap.
	•	A SMTP server (e.g., Gmail) for sending email alerts.

Setup and Installation

	1.	Clone the repository:
      git clone https://github.com/your-username/weather-monitoring.git
      cd weather-monitoring
	2.	Set up the OpenWeatherMap API key:
      Open the application.properties file and add your OpenWeatherMap API key:
      weather.api.key=YOUR_API_KEY_HERE
	3.	Set up email notifications:
      Configure your SMTP email service (e.g., Gmail) in the application.properties file:
      spring.mail.host=smtp.gmail.com
      spring.mail.port=587
      spring.mail.username=your-email@gmail.com
      spring.mail.password=your-app-specific-password
      spring.mail.properties.mail.smtp.auth=true
      spring.mail.properties.mail.smtp.starttls.enable=true
      Note: If you have 2FA enabled, use an app-specific password instead of your regular password.
  4.  Run the application:
        •	Using Maven:
          mvn spring-boot:run

	  

Future Enhancements

	•	Support for additional weather parameters such as humidity and wind speed.
	•	Integration with a frontend framework for better visualizations.
	•	Weather forecasting summaries using OpenWeatherMap’s forecast data.
	•	Push notifications for alerts.

      
