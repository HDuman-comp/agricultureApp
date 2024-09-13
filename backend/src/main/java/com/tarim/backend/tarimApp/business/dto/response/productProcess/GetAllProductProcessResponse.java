package com.tarim.backend.tarimApp.business.dto.response.productProcess;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllProductProcessResponse {

    private int id;
    private String productName;
    private String productProcessName;
    private List<Integer> days;
  
}
