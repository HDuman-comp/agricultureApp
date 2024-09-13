package com.tarim.backend.tarimApp.business.dto.request.Parcel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateParcelDetailRequest {

    private int id;
    private String parcelNumber;
    private boolean status;
    private Integer userId;

    
}
