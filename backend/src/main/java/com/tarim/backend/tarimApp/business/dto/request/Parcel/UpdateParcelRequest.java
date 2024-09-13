package com.tarim.backend.tarimApp.business.dto.request.Parcel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class UpdateParcelRequest {

    private int id;
    //private int productId;
   // private int userId;
    private boolean status;

    
    
}
