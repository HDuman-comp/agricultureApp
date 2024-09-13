package com.tarim.backend.tarimApp.business.dto.response.user;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VariableParcelOwnedByUser {

    private String parcelNumber;
    private String productName;
    private List<VariableProductWithProcessForUser> parcelTasks;
    
}
