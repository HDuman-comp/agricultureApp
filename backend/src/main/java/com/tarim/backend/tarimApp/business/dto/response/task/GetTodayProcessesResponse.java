package com.tarim.backend.tarimApp.business.dto.response.task;

import java.time.LocalDate;
import java.util.List;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetTodayProcessesResponse {

    private LocalDate today;
    private int currentDay;
    private String parcelNumber;
    //private String productName;
    private List<VariableGetTodayProcesses> tasks;

    
}
