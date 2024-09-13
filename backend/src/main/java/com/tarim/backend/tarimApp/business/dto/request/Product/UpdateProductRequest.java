package com.tarim.backend.tarimApp.business.dto.request.Product;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class UpdateProductRequest {
    private int id;
    private String productName;
    private LocalDate demandStartDate;
    private LocalDate demandEndDate;
    private LocalDate startDate;
    private double price;
    private String imageUrl;
}
