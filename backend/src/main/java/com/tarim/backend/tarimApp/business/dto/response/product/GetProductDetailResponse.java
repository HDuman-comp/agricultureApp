package com.tarim.backend.tarimApp.business.dto.response.product;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class GetProductDetailResponse {
    private int id;
    private String productName;
    private LocalDate demandStartDate;
    private LocalDate demandEndDate;
    private LocalDate startDate;
    private LocalDate endDate;
    private double price;
    private String imageUrl;
    private int productGrowingTime;
    private boolean isActive;
    private List<VariableProductDetail> productProcesses;
    
}
