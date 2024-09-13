package com.tarim.backend.tarimApp.business.dto.response.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindActiveProductResponse {
    
    private int id;
    private String productName;
    private String imageUrl;

}
