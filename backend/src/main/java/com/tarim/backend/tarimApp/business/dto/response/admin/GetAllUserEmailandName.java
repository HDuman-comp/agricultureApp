package com.tarim.backend.tarimApp.business.dto.response.admin;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllUserEmailandName {
    private String email;
    private String firstname;
    private String lastname;

}
