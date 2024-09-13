package com.tarim.backend.tarimApp.business.dto.response.task;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VariableGetTodayProcesses {

    private int id;
    private String processName;
    private List<VariableGetTodayProcessesDay> taskDays;


    
}
