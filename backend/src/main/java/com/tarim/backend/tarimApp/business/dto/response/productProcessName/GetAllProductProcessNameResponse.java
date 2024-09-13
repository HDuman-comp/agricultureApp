package com.tarim.backend.tarimApp.business.dto.response.productProcessName;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllProductProcessNameResponse {

    private int id;
    private String name;
    //private List<Integer> productProcessDay;
    
}
