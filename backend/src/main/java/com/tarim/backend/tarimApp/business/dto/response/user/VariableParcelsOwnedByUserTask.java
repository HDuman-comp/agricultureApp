package com.tarim.backend.tarimApp.business.dto.response.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class VariableParcelsOwnedByUserTask {

    private int id;
    private int day;
    private boolean completed;
    
}
