package com.tarim.backend.tarimApp.business.dto.response.parcel;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllParcelResponse {
    
    private int id;
    private String parcelNumber;
    private String productName;
    private boolean status;
    private String userFirstName;
    private String userLastName;
    


}
