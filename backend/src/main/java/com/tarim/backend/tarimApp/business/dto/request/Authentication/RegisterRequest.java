package com.tarim.backend.tarimApp.business.dto.request.Authentication;

import com.tarim.backend.tarimApp.business.dto.response.user.VariableAddresForUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String image;
    private String phone;
    private List<VariableAddresForUser> addresses;

}
