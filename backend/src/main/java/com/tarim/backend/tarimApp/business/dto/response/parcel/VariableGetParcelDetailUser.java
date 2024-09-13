package com.tarim.backend.tarimApp.business.dto.response.parcel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VariableGetParcelDetailUser {

    private String addressCity;
    private String addressDistrict;
    private String addressDetail;

    
}
