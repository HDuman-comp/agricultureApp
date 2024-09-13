package com.tarim.backend.tarimApp.business.dto.request.User;

import com.tarim.backend.tarimApp.business.dto.response.user.VariableAddresForUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditUserInfoRequest {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String image;
    private String phone;
    private String password;
    private List<VariableAddresForUser> addresses;
}
