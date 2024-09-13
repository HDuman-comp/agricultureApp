package com.tarim.backend.tarimApp.business.dto.request.Parcel;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateParcelRequest {

    @NotNull  //validation constraints 
    @NotBlank
    @Size(min = 3, max = 20 )
    private String parcelNumber;
    private int productId;
    
}
