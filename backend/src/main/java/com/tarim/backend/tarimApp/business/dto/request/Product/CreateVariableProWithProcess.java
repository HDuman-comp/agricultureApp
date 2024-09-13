package com.tarim.backend.tarimApp.business.dto.request.Product;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateVariableProWithProcess {

    private int productProcessNameId;
    private List<Integer> days;

    
}
