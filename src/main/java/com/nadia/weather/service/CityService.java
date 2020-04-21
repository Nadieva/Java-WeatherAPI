package com.nadia.weather.service;

import com.nadia.weather.entity.city.City;
import com.nadia.weather.repository.city.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CityService {
    @Autowired
    private CityRepository cities;


    public Iterable<City> getAllCities() {
        return cities.findAll();
    }

    public void saveCity(String city, String country) {

        City c = City.builder()
                .cityName(city)
                .country(country)
                .build();
        cities.save(c);
    }




    public City getCityById(Long id) {
        Optional<City> city = cities.findById(id);
        return city.get();
    }

    public City getCity(String cityName, String country) {
        List<City> foundcities = new ArrayList<>();
        cities.findAll().forEach(foundcities::add); // add each found city  into foundcities
        for (City c : foundcities) {
            if (cityName.equalsIgnoreCase(c.getCityName()) && country.equalsIgnoreCase(c.getCountry())) {
                return c;
            }
        }
        return null;
    }

    //update or add new city
    public void saveCity(City c) {
        cities.save(c);
    }

    public void deleteCityById(Long id) {
        cities.deleteById(id);
    }
}
