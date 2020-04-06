package com.nadia.weather.repository.city;

import com.nadia.weather.entity.city.City;
import org.springframework.data.repository.CrudRepository;

/*
Repository that holds  city records with its region and country
Every city has its id
*/
public interface CityRepository extends CrudRepository<City,Long> {

}
