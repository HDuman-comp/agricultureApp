package com.tarim.backend.tarimApp.business.dto.request.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddProductsToParcelRequest {

    private int id;
    private int productId;
    
}
