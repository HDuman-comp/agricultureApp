package com.tarim.backend.tarimApp.business.dto.request.ParcelImage;




import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UpdateParcelImageRequest {

    private int Id;   
    private String imageUrl;
    
}
