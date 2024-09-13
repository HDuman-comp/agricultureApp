package com.tarim.backend.tarimApp.dataAccess.abstracts;

import com.tarim.backend.tarimApp.core.entities.User;
import com.tarim.backend.tarimApp.core.entities.Weather;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IWeatherRepository extends JpaRepository<Weather, Integer> {
    List<Weather> findByCity(String city);
}
