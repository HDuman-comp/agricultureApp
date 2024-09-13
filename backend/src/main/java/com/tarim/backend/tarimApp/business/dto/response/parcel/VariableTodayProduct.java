package com.tarim.backend.tarimApp.business.dto.response.parcel;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VariableTodayProduct {

    private String productName;
    private int currentDay;
    private VariableTodayProductStatus productStatuses;


    
}
