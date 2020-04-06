package com.nadia.weather.entity.weather;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity // This tells Hibernate to make a table out of this class
public class WeatherProvider {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name="forecast_date")
    private LocalDate weatherDate;
    @Column(name="forecast_time")
    private LocalTime weatherTime;
    private String city;
    @Column(name="region")
    private String subCountry;
    private String country;
    @Column(name="provider_name")
    private String providerName;
    private String summary;
    @Column(name="temperature")
    private String tempAverageInCelsius;
    @Column(name="min_temperature")
    private String minTemperatureInCelsius;
    @Column(name="max_temperature")
    private String maxTemperatureInCelsius;
    private String humidity;
    private String pressure;
    private String visibility;
    private String cloudiness;
    @Column(name="wind_speed")
    private String windSpeed;
    @Column(name="wind_degree")
    private String windDegree;
    @Column(name="last_datetime_updated")
    private LocalDateTime lastUpdated;
}
