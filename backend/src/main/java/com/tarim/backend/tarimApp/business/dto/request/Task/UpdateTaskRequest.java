package com.tarim.backend.tarimApp.business.dto.request.Task;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTaskRequest {

    private int id;
    private LocalDate date ;
    private int taskDetailId;
    private int parcelId;
    private boolean status;
}
