package com.tarim.backend.tarimApp.webApi.controllers;

import java.util.List;

import com.tarim.backend.tarimApp.business.dto.request.User.EditUserInfoRequest;
import com.tarim.backend.tarimApp.business.dto.response.user.GetUserInfoResponse;
import com.tarim.backend.tarimApp.core.entities.User;
import com.tarim.backend.tarimApp.dataAccess.abstracts.UserRepository;
import org.springframework.web.bind.annotation.*;

import com.tarim.backend.tarimApp.business.concretes.abstracts.IUserService;
import com.tarim.backend.tarimApp.business.dto.request.User.AddProductsToParcelRequest;
import com.tarim.backend.tarimApp.business.dto.response.user.GetAllParcelForUserResponse;
import com.tarim.backend.tarimApp.business.dto.response.user.GetAllUserResponse;
import com.tarim.backend.tarimApp.business.dto.response.user.ParcelsOwnedByUserResponse;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/users")
@AllArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private IUserService userService;

    @GetMapping("/getAllUser")
    public List<GetAllUserResponse> getAllUser(){
        return this.userService.getAll();
        
    }

    
    @GetMapping("/getParcelOwendByUser/{userId}")
    public ParcelsOwnedByUserResponse getParcelsOwnedByUser (int userId){
        return this.userService.getParcelsOwnedByUser(userId);
    }
    

    @PutMapping("/addProductToParcel")
    public void addProductsToParcel (@RequestBody AddProductsToParcelRequest addProductsToParcelRequest){
        this.userService.addProductsToParcel(addProductsToParcelRequest);
    }

    @GetMapping("/getAllParcelforUser/{userId}")
    public List<GetAllParcelForUserResponse> getAllParcelsForUser(int userId){

        return this.userService.getAllParcelForUser(userId);
    }

   @GetMapping("/getUserInfo/{userId}")
    public GetUserInfoResponse getUserInfo (@PathVariable int userId){
        return this.userService.getUserInfo(userId);
   }

    @PutMapping("/updateProfile")
    public void updateProfile(@RequestBody EditUserInfoRequest editUserInfoRequest) {
        User user = userRepository.findById(editUserInfoRequest.getId()).get();
        userService.EditUserInfo(user, editUserInfoRequest);
    }


    
    
}
