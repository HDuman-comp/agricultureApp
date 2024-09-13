package com.tarim.backend.tarimApp.business.mapper;



import com.tarim.backend.tarimApp.business.dto.request.User.EditUserInfoRequest;
import com.tarim.backend.tarimApp.core.entities.User;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import com.tarim.backend.tarimApp.business.dto.response.parcel.GetAllParcelByDateResponse;
import com.tarim.backend.tarimApp.core.entities.Parcel;

import lombok.AllArgsConstructor;


@AllArgsConstructor
@Service
public class ModelMapperService  implements IModelMapperService{

    private ModelMapper modelMapper;

    @Override
    public ModelMapper forRequest() {
        this.modelMapper.getConfiguration()
        .setAmbiguityIgnored(true)
        .setMatchingStrategy(MatchingStrategies.STANDARD);

        return this.modelMapper;
    }

    @Override
    public ModelMapper forResponse() {
        this.modelMapper.getConfiguration()
        .setAmbiguityIgnored(true)
        .setMatchingStrategy(MatchingStrategies.LOOSE);
        
        
        return this.modelMapper;
    }

    public void update(Object source, Object destination) {
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        modelMapper.map(source, destination);
    }

    public GetAllParcelByDateResponse parcelToParcelByDate(Parcel parcel, String  productStage) {
        this.modelMapper.getConfiguration(); 
        GetAllParcelByDateResponse parcelByDateResponse = this.modelMapper.map(parcel, GetAllParcelByDateResponse.class);
        parcelByDateResponse.setProductStageName(productStage);
        

        return parcelByDateResponse;
    }

    public GetAllParcelByDateResponse parcelToParcelByDate2(Parcel parcel, String  productStage, String image, int day , int id) {
        this.modelMapper.getConfiguration(); 
        GetAllParcelByDateResponse parcelByDateResponse = this.modelMapper.map(parcel, GetAllParcelByDateResponse.class);
        parcelByDateResponse.setProductStageName(productStage);
        parcelByDateResponse.setParcelImageUrl(image);
        parcelByDateResponse.setProductGrowingTime(day);
        parcelByDateResponse.setProductImageId(id);
        
        
        return parcelByDateResponse;
    }

    


    
    
}
