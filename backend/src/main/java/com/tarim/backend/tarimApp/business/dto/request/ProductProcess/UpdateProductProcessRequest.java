package com.tarim.backend.tarimApp.business.dto.request.ProductProcess;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductProcessRequest {
    
    private int id;
    private int productId;
    private int productProcessNameId;
    private List<Integer> days;
}
