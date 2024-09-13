package com.tarim.backend.tarimApp.business.concretes;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tarim.backend.tarimApp.business.concretes.abstracts.ICityService;
import com.tarim.backend.tarimApp.business.dto.response.cityDistrict.GetAllCityDistrictResponse;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CityService implements ICityService {

    private static final String JSON_FILE_PATH = "backend/src/main/java/com/tarim/backend/tarimApp/datas/ilceler.json";

    @Override
    public List<GetAllCityDistrictResponse> getAllCitiesAndDistricts() {
        List<GetAllCityDistrictResponse> cityDistrictList = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, List<String>> cityMap = new HashMap<>();

        try {
            List<Map<String, String>> ilceler = objectMapper.readValue(new File(JSON_FILE_PATH), new TypeReference<List<Map<String, String>>>() {});

            for (Map<String, String> ilce : ilceler) {
                String ilceAdi = ilce.get("ilce_adi");
                String sehirAdi = ilce.get("sehir_adi");

                cityMap.computeIfAbsent(sehirAdi, k -> new ArrayList<>()).add(ilceAdi);
            }

            for (Map.Entry<String, List<String>> entry : cityMap.entrySet()) {
                cityDistrictList.add(new GetAllCityDistrictResponse(entry.getValue(), entry.getKey()));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return cityDistrictList;
    }


}
