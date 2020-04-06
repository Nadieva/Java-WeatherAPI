package com.nadia.weather.repository.weather;

import com.nadia.weather.entity.weather.WeatherProvider;
import org.springframework.data.repository.CrudRepository;

/*
Repository that holds the weather  forecast records given by all providers
Every weather forecast has its id
*/
public interface WeatherProviderRepository extends CrudRepository<WeatherProvider,Long> {
}
