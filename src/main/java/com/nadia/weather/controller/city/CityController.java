package com.nadia.weather.controller.city;


import com.nadia.weather.entity.city.City;
import com.nadia.weather.entity.weather.WeatherProvider;
import com.nadia.weather.service.CityService;
import com.nadia.weather.service.WeatherProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller // This means that this class is a Controller
@RequestMapping(path="/cities")
public class CityController {

    @Autowired
    private CityService cityservice;

    @Autowired
    private WeatherProviderService weatherProviderService;

    //get to city form page
    @GetMapping(value = "/form" )
    public  String getCityForm(){
        return "cityform";
    }


    //create city to get weather forecasts
    @PostMapping(value = "/form")
    public  String getCityForm(@ModelAttribute( name="cityForm") City city, Model model) {
        String cityName= city.getCityName();
        String countryName= city.getCountry();

        if(cityName ==null || cityName ==""){
            model.addAttribute("isCityNameNull","true");
            return "cityform";
        }

        if(countryName == null || countryName ==""){
            model.addAttribute("isCountryNameNull","true");
            return "cityform";
        }

       List<WeatherProvider> forecastList=weatherProviderService.getAllWeatherForecastsByCityAndCountry(cityName,countryName);

        if (forecastList.isEmpty() || forecastList == null) {
            return "error";
        }

        model.addAttribute("cityName",cityName);
        model.addAttribute("country", countryName);
        model.addAttribute("forecasts",forecastList);
        return "forecasts";
    }

    @GetMapping
    public @ResponseBody Iterable<City> getAllCities() {
        return cityservice.getAllCities();
    }

    @PostMapping
    public @ResponseBody String createCity (@RequestParam String city
            , @RequestParam String country) {
            cityservice.saveCity(city,country);
            return city+"("+country+") saved";
    }

    @GetMapping(path="/{id}")
    public @ResponseBody
    City  getCity(@PathVariable String id) {
        Long long_id=Long.valueOf(id);
        return cityservice.getCityById(long_id);
    }

    @PutMapping(path="/{id}")
    public String updateCity(@RequestBody City city, @PathVariable String id) {
        cityservice.saveCity(city);
        return "City #"+id +" updated";
    }


    @DeleteMapping(path="/{id}")
    public String deleteCityById(@PathVariable String id) {
        Long long_id=Long.valueOf(id);
        cityservice.deleteCityById(long_id);
        return "City #"+id +" deleted";
    }
}
