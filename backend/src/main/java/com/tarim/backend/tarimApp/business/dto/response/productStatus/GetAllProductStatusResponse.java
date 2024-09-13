package com.tarim.backend.tarimApp.business.dto.response.productStatus;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllProductStatusResponse {
    
    private int id;

    private String productProductName;

    private String productStageName;

    private List<Integer> days;
}
