package com.tarim.backend.tarimApp.webApi.controllers;


import com.tarim.backend.tarimApp.business.concretes.CityService;
import com.tarim.backend.tarimApp.business.dto.response.cityDistrict.GetAllCityDistrictResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/cities")
public class CityController {
    private  CityService cityService;

    @GetMapping("/getCityandDistrict")
    public List<GetAllCityDistrictResponse> getCityAndDistrict() {
        return cityService.getAllCitiesAndDistricts();
    }

}
