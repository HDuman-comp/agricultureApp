package com.tarim.backend.tarimApp.business.Scheduling;

import com.tarim.backend.tarimApp.business.concretes.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class WeatherScheduler {
    @Autowired
    private WeatherService weatherService;

    @Scheduled(cron = "0 0 6,12,18 * * ?")
    public void fetchWeatherData() {
        weatherService.fetchAndSaveWeatherData("denizli");
        // İstediğiniz başka şehirler için de benzer çağrılar yapabilirsiniz
    }
}
