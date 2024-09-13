package com.tarim.backend.tarimApp.business.concretes.abstracts;

import com.tarim.backend.tarimApp.business.dto.response.cityDistrict.GetAllCityDistrictResponse;

import java.util.List;

public interface ICityService {
    List<GetAllCityDistrictResponse> getAllCitiesAndDistricts();

}
