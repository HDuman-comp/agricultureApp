package com.tarim.backend.tarimApp.business.dto.request.Task;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTaskRequest {
    
    private int parcelId;
    private int taskDetailId;
    private LocalDate date;
    

}
