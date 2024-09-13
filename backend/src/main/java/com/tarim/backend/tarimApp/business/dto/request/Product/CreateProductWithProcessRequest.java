package com.tarim.backend.tarimApp.business.dto.request.Product;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductWithProcessRequest {


    private String productName;
    private LocalDate demandStartDate;
    private LocalDate demandEndDate;
    private LocalDate startDate;
    private int growingTime;
    private double price;
    private String imageUrl;
    private List<Integer> kademe;
    private List<CreateVariableProWithProcess> productProcesses;
}
