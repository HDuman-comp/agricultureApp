package com.tarim.backend.tarimApp.business.dto.response.parcel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VariableTodayTaskDay {

    private int id;
    private int day;
    private boolean completed;
    
}
