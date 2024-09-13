package com.tarim.backend.tarimApp.business.concretes.abstracts;

import java.util.List;

import com.tarim.backend.tarimApp.business.dto.request.User.AddProductsToParcelRequest;
import com.tarim.backend.tarimApp.business.dto.request.User.EditUserInfoRequest;
import com.tarim.backend.tarimApp.business.dto.response.user.GetAllParcelForUserResponse;
import com.tarim.backend.tarimApp.business.dto.response.user.GetAllUserResponse;
import com.tarim.backend.tarimApp.business.dto.response.user.GetUserInfoResponse;
import com.tarim.backend.tarimApp.business.dto.response.user.ParcelsOwnedByUserResponse;
import com.tarim.backend.tarimApp.core.entities.User;

public interface IUserService {

    List<GetAllUserResponse> getAll();
    ParcelsOwnedByUserResponse getParcelsOwnedByUser (int userId);
    void addProductsToParcel (AddProductsToParcelRequest request);

    List<GetAllParcelForUserResponse> getAllParcelForUser(int userId);

    GetUserInfoResponse getUserInfo (int userId);

    void EditUserInfo (User user, EditUserInfoRequest request);



    
    
    
}
