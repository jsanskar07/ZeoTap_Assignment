package com.weatherMonitoring.weatherMonitoring.Repository;

import com.weatherMonitoring.weatherMonitoring.Entity.WeatherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface WeatherRepository extends JpaRepository<WeatherEntity, Long> {
    List<WeatherEntity> findByCityAndTimestampBetween(String city, Date start, Date end);
}
