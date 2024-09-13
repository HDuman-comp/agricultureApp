package com.tarim.backend.tarimApp.webApi.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tarim.backend.tarimApp.business.concretes.abstracts.IParcelImageService;
import com.tarim.backend.tarimApp.business.dto.response.parcelImage.GetAllParcelImageResponse;
import com.tarim.backend.tarimApp.core.payload.FileResponse;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("api/parcelImage")
@AllArgsConstructor
public class ParcelImageController {

    private IParcelImageService parcelImageService;


    @GetMapping("getall")
    public List<GetAllParcelImageResponse> getAll(){

        return this.parcelImageService.getAll();

    }

    @PutMapping(path = "/updateImageParcelbyDate/{id}", consumes = {"multipart/form-data"} )
    public FileResponse parcelImageRepository( @RequestPart("file") MultipartFile multipartFile, @PathVariable("id") int id ) {
        return this.parcelImageService.updateParcelImage(multipartFile,id);
    }


    @PutMapping("/removeObject/{id}")
    public String removeImage (int id){
        boolean check = this.parcelImageService.deleteImageParcel(id);

        if(check){
            return " Delete List Object successfully ";
        }else {
            return " Delete failed ";
        }

    }




    
}
