package com.tarim.backend.tarimApp.business.concretes.abstracts;

import com.tarim.backend.tarimApp.business.dto.response.admin.GetAllUserEmailandName;
import com.tarim.backend.tarimApp.business.dto.response.admin.GetUserAllDetailResponse;

import java.util.List;

public interface IAdminService {

    List<GetAllUserEmailandName> getAllUserEmailandName();
    GetUserAllDetailResponse getUserAllDetail(int id);
}
