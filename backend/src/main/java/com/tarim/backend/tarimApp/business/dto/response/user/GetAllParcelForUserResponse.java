package com.tarim.backend.tarimApp.business.dto.response.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class GetAllParcelForUserResponse {

    private int parcelId;
    private String parcelNumber;
    private boolean parcelStatus;


    
}