package com.tarim.backend.tarimApp.business.dto.response.user;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllUserResponse {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private List<VariableAddresForUser> addresses;

    
    
}
