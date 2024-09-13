package com.tarim.backend.tarimApp.business.dto.response.parcel;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetTodayTaskForParcel {

    private int parcelId;
    private String parcelNumber;
    private boolean status;
    private VariableTodayProduct product; 
    private VariableTodayTaskImageParcel parcelImages;
    private List<VariableTodayTask> tasks;
    
    
}
