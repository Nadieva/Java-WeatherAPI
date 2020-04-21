package com.nadia.weather.entity.weather;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class WeatherProvider {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @Column(name = "forecast_date")
    private LocalDate weatherDate;
    @Column(name = "forecast_time")
    private LocalTime weatherTime;
    private String city;
    private String country;
    @Column(name = "provider_name")
    private String providerName;
    private String summary;
    @Column(name = "average_temperature_celsius")
    private String tempAverageInCelsius;
    @Column(name = "min_temperature_celsius")
    private String minTemperatureInCelsius;
    @Column(name = "max_temperature_celsius")
    private String maxTemperatureInCelsius;
    @Column(name = "average_temperature_fahrenheit")
    private String tempAverageInFahrenheit;
    @Column(name = "min_temperature_fahrenheit")
    private String minTemperatureInFahrenheit;
    @Column(name = "max_temperature_fahrenheit")
    private String maxTemperatureInFahrenheit;
    private String humidity;
    private String pressure;
    private String visibility;
    private String cloudiness;
    @Column(name = "wind_speed")
    private String windSpeed;
    @Column(name = "wind_degree")
    private String windDegree;
    @Column(name = "uv_index")
    private String uvIndex;

    @Column(name = "last_datetime_updated")
    private LocalDateTime lastUpdatedDateTime;
}
