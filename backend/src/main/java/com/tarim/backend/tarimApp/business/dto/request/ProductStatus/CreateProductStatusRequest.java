package com.tarim.backend.tarimApp.business.dto.request.ProductStatus;



import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductStatusRequest {
    
    private int productId;
    private int productStageId;
    private List<Integer> days;
}
