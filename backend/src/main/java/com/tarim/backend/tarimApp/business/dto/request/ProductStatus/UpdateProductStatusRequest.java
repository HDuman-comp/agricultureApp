package com.tarim.backend.tarimApp.business.dto.request.ProductStatus;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdateProductStatusRequest {
    private int id;
    private int productId;
    private int productStageId;
    private List<Integer> days;
    
}
