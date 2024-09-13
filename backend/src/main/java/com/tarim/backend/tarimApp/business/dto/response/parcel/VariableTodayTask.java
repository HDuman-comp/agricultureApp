package com.tarim.backend.tarimApp.business.dto.response.parcel;


import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VariableTodayTask {

    private int taskId;
    private String productProcessName;
    private List <VariableTodayTaskDay> taskDays;
    
}
