package com.tarim.backend.tarimApp.business.dto.response.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VariableGetTodayProcessesDay {

    private int id;
    private int day;
    private boolean completed;

     
}