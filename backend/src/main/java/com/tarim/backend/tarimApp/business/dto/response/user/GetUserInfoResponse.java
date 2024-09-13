package com.tarim.backend.tarimApp.business.dto.response.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUserInfoResponse {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private List<VariableAddresForUser> addresses;
}
