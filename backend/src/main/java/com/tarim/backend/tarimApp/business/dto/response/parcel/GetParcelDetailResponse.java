package com.tarim.backend.tarimApp.business.dto.response.parcel;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetParcelDetailResponse {

    private int id;
    private String parcelNumber;
    private boolean status;
    private String productName;
    private long currentDay;
    private String userFirstName;
    private String userLastName;
    private String userEmail;
    private String userPhone;
    private List<VariableGetParcelDetailUser> userAddresses;
    private VariableDetailProductStatus productStatuses;
    private int userId;  // Add this field
    //private String adressCity;
}
