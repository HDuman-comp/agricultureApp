package com.tarim.backend.tarimApp.business.dto.response.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VariableAddresForUser {

    private String addressCity;
    private String addressDistrict;
    private String addressDetail;

    
}
