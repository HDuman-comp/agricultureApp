package com.tarim.backend.tarimApp.webApi.controllers;

import com.tarim.backend.tarimApp.business.concretes.WeatherService;
import com.tarim.backend.tarimApp.core.entities.Weather;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/weather")
public class WeatherController {
    private final WeatherService weatherService;
    // Hava durumu verilerini API'den çekip veritabanına kaydeder
    @GetMapping("/fetch")
    public String fetchWeatherData(@RequestParam String city) {
        weatherService.fetchAndSaveWeatherData(city);
        return "Weather data for " + city + " has been fetched and saved.";
    }

    // Veritabanındaki belirli bir şehrin hava durumu verilerini döner
    @GetMapping("/get")
    public List<Weather> getWeatherData(@RequestParam String city) {
        return weatherService.getWeatherByCity(city);
    }

}
