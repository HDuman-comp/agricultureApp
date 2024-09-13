package com.tarim.backend.tarimApp.business.dto.request.ProductStage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductStageRequest {
    
    private int id;
    private String name;
}
