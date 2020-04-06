package com.nadia.weather.controller.weather;

import com.nadia.weather.entity.weather.WeatherProvider;
import com.nadia.weather.service.WeatherProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller // This means that this class is a Controller
@RequestMapping(path = "/providers")
public class WeatherProviderController {

    @Autowired
    // Which is auto-generated by Spring, we will use it to handle the data
    private WeatherProviderService weatherservice;


    @GetMapping
    public @ResponseBody
    Iterable<WeatherProvider> getAllWeatherForecasts() {
        // This returns a JSON (or XML) with weather providers
        return weatherservice.getAllWeatherForecasts();
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody
    WeatherProvider findWeatherForecastById(@PathVariable String id) {
        // This returns a JSON (or XML) with weather providers
        Long longId = Long.valueOf(id);
        return weatherservice.getProviderById(longId);
    }

    @DeleteMapping(path = "/{id}")
    public @ResponseBody
    String deleteProviderById(@PathVariable String id) {
        // This returns a JSON (or XML) with weather providers
        Long longId = Long.valueOf(id);
        weatherservice.deleteProviderById(longId);
        return "Weather provider #" + id + " deleted";
    }


    @GetMapping(path = "/cities")
    public @ResponseBody
    List<WeatherProvider> getAllWeatherForecastsByCityAndCountry(@RequestParam String city
            , @RequestParam String country) {
        return weatherservice.getAllWeatherForecastsByCityAndCountry(city, country);
    }

    @GetMapping(path = "/open")
    public @ResponseBody
    WeatherProvider getWeatherForecastByOpenWeatherFromOpenAPI(@RequestParam String city, @RequestParam String country) {
        return weatherservice.findWeatherForecastByCityAndCountryFromOpenAPI(city, country);
    }

    /* Accu  API issue
    @GetMapping(path="/accu")
    public @ResponseBody WeatherProvider getWeatherForecastByAccuWeatherFromAccuAPI(@RequestParam String city, @RequestParam String country) {
        return weatherservice.findWeatherForecastByCityAndCountryFromAccuAPI(city, country);
    }
   */

    @DeleteMapping
    public void deleteAllProviders() {
        weatherservice.deleteAllProviders();
    }


}