package com.tarim.backend.tarimApp.business.dto.response.parcel;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class GetAllParcelByDateResponse {

    private int productImageId;
    private int parcelId;
    private String parcelNumber;
    private String productName;
    private int productGrowingTime;
    private String productStageName;
    private String parcelImageUrl;
   
        
}
