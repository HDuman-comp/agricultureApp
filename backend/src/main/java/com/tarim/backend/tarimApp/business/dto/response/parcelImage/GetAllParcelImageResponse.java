package com.tarim.backend.tarimApp.business.dto.response.parcelImage;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllParcelImageResponse {

    private int id;
    private String parcelNumber;
    private LocalDate date;
    private String url;


    
}
