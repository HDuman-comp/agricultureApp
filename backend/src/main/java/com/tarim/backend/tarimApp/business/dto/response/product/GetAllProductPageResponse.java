package com.tarim.backend.tarimApp.business.dto.response.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class GetAllProductPageResponse {

    private int id;
    private String productName;
    private String imageUrl;
    private boolean active;
    
}
