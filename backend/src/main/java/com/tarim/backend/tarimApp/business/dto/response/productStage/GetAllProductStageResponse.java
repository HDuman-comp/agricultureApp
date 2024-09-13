package com.tarim.backend.tarimApp.business.dto.response.productStage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllProductStageResponse {
    private int id;
    private String name;
}
