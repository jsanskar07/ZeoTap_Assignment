package com.weatherMonitoring.weatherMonitoring.Repository;

import com.weatherMonitoring.weatherMonitoring.Entity.DailyWeatherSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailySummaryRepository extends JpaRepository<DailyWeatherSummary, Long> {
}
