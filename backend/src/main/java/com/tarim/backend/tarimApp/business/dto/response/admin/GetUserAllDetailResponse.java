package com.tarim.backend.tarimApp.business.dto.response.admin;

import com.tarim.backend.tarimApp.business.dto.response.user.GetUserInfoResponse;
import com.tarim.backend.tarimApp.business.dto.response.user.VariableAddresForUser;
import com.tarim.backend.tarimApp.business.dto.response.user.VariableParcelOwnedByUser;
import com.tarim.backend.tarimApp.business.dto.response.user.VariableProductWithProcessForUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUserAllDetailResponse {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private List<VariableAddresForUser> addresses;
    private String parcelNumber;
    private String productName;
    private List<VariableProductWithProcessForUser> parcelTasks;
}
