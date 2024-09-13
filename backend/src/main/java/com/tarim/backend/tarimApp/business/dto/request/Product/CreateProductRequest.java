package com.tarim.backend.tarimApp.business.dto.request.Product;

import java.time.LocalDate;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

 
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductRequest {


    @NotNull
    @NotBlank
    @Size(min = 3, max = 25)
    private String productName;
    @NotNull
    //@Past
    private LocalDate demandStartDate;
    @NotNull
    //@Future
    private LocalDate demandEndDate;
    @NotNull
    //@FutureOrPresent
    private LocalDate startDate;
    @Min(value = 0, message = "Price should not be less than")
    private double price;
    private String imageUrl;

    private String productProcessName;

    
}
