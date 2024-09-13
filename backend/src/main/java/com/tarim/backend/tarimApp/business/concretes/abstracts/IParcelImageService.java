package com.tarim.backend.tarimApp.business.concretes.abstracts;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.tarim.backend.tarimApp.business.dto.response.parcelImage.GetAllParcelImageResponse;
import com.tarim.backend.tarimApp.core.payload.FileResponse;

public interface IParcelImageService {

    List<GetAllParcelImageResponse> getAll();

    FileResponse updateParcelImage (MultipartFile multipartFile,int id);

    boolean deleteImageParcel(int id);
    
}
