package com.tarim.backend.tarimApp.webApi.controllers;

import com.tarim.backend.tarimApp.business.concretes.AdminService;
import com.tarim.backend.tarimApp.business.dto.response.admin.GetAllUserEmailandName;
import com.tarim.backend.tarimApp.business.dto.response.admin.GetUserAllDetailResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {
    private final AdminService adminService;

    @GetMapping("/getUserNameandEmail")
    public List<GetAllUserEmailandName> getUserNameandEmail() {
        return this.adminService.getAllUserEmailandName();
    }

    @GetMapping("/getUserAllDetail")
    public GetUserAllDetailResponse getUserAllDetail(int id) { return this.adminService.getUserAllDetail(id);}
}
