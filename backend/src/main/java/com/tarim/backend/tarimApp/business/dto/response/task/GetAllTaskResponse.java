package com.tarim.backend.tarimApp.business.dto.response.task;

import java.time.LocalDate;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllTaskResponse {
    
    private int id;
    private LocalDate date ;
    private String taskDetailName;
    private String parcelNumber;
    private boolean status;
    

    

}
