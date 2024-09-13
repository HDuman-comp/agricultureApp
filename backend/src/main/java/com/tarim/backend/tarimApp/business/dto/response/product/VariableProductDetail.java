package com.tarim.backend.tarimApp.business.dto.response.product;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VariableProductDetail {

    private int productProcessNameId;
    private String productProcessName;
    private int totalDay;
    private List<Integer> days;

    
}
