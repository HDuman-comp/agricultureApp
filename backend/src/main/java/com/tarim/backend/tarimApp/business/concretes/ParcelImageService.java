package com.tarim.backend.tarimApp.business.concretes;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tarim.backend.tarimApp.business.concretes.abstracts.IParcelImageService;
import com.tarim.backend.tarimApp.business.dto.response.parcelImage.GetAllParcelImageResponse;
import com.tarim.backend.tarimApp.business.exception.FileResponseException;
import com.tarim.backend.tarimApp.business.mapper.ModelMapperService;
import com.tarim.backend.tarimApp.business.minio.IMinioService;
import com.tarim.backend.tarimApp.core.entities.ParcelImage;
import com.tarim.backend.tarimApp.core.payload.FileResponse;
import com.tarim.backend.tarimApp.core.utilities.FileTypeUtils;
import com.tarim.backend.tarimApp.dataAccess.abstracts.IParcelImageRepository;



import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class ParcelImageService implements IParcelImageService {

    private IParcelImageRepository parcelImageRepository;
    private ModelMapperService modelMapperService;
    private IMinioService minioService;
    
    
    
    @Override
    public List<GetAllParcelImageResponse> getAll() {
        List<ParcelImage> parcelImages = this.parcelImageRepository.findAll();
        List<GetAllParcelImageResponse> getAllParcelImageResponses = parcelImages.stream()
        .map(parcelImage->this.modelMapperService.forResponse()
        .map(parcelImage, GetAllParcelImageResponse.class)).collect(Collectors.toList());
        return getAllParcelImageResponses;
        
    }


    @Override
    public FileResponse updateParcelImage(MultipartFile multipartFile, int id) {

        Optional <ParcelImage> parcelImage = this.parcelImageRepository.findById(id);

        String bucketName = parcelImage.get().getParcel().getParcelNumber();
        bucketName = bucketName.replaceAll("\\s+", "").toLowerCase();
        
        String fileType = FileTypeUtils.getFileType(multipartFile);

        if(fileType != null){

            FileResponse fileResponse = minioService.putObject(multipartFile,bucketName, fileType);
            parcelImage.get().setImageUrl(fileResponse.getUrl());
            this.parcelImageRepository.save(parcelImage.get());
            return fileResponse;

        }

        throw new FileResponseException("File cannot be upload.");

       

        /* 
        Optional <Parcel> parcel = this.parcelRepository.findById(updateParcelImageRequest.getParcelId());
        ParcelImage parcelImage = this.modelMapperService.forRequest().map(updateParcelImageRequest, ParcelImage.class);
        
        this.parcelRepository.save(parcel.get());
        
        this.parcelImageRepository.save(parcelImage);
        */
    }


    @Override
    public boolean deleteImageParcel(int id) {

        Optional <ParcelImage> parcelImage = this.parcelImageRepository.findById(id);

        String bucketName = parcelImage.get().getParcel().getParcelNumber();
        bucketName = bucketName.replaceAll("\\s+", "").toLowerCase();

        String url = parcelImage.get().getImageUrl();
        Path path = Paths.get(url);
        


        parcelImage.get().setImageUrl(null);

        this.parcelImageRepository.save(parcelImage.get());
        return minioService.removeObject(bucketName, path.getFileName().toString());


    }


}
