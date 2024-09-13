package com.tarim.backend.tarimApp.business.dto.response.cityDistrict;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class GetAllCityDistrictResponse {
    private List<String> ilceler;
    private String sehirAdi;
}
