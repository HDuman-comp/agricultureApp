package com.tarim.backend.tarimApp.business.dto.request.ProductProcess;



import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductProcessRequest {

    private int productId;
    private int ProductProcessNameId;
    private List<Integer> days;

    
}
