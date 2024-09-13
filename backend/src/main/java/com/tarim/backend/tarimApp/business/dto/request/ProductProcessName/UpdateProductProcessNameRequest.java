package com.tarim.backend.tarimApp.business.dto.request.ProductProcessName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UpdateProductProcessNameRequest {

    private int id;
    private String name;
    
}
