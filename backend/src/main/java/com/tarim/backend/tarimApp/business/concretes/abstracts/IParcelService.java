package com.tarim.backend.tarimApp.business.concretes.abstracts;

import java.time.LocalDate;
import java.util.List;

import com.tarim.backend.tarimApp.business.dto.request.Parcel.UpdateParcelDetailRequest;
//import com.tarim.backend.tarimApp.business.dto.request.Parcel.CreateParcelRequest;
import com.tarim.backend.tarimApp.business.dto.request.Parcel.UpdateParcelRequest;
import com.tarim.backend.tarimApp.business.dto.response.parcel.GetAllParcelByDateResponse;
import com.tarim.backend.tarimApp.business.dto.response.parcel.GetAllParcelResponse;
import com.tarim.backend.tarimApp.business.dto.response.parcel.GetParcelDetailResponse;
import com.tarim.backend.tarimApp.business.dto.response.parcel.GetTodayTaskForParcel;


public interface IParcelService {

    List<GetAllParcelResponse> getAll();
    //void add(CreateParcelRequest createParcelRequest);
    void update (UpdateParcelRequest updateParcelRequest);
    void delete (int id);
    
    
    

    
    List<GetAllParcelByDateResponse> GetAllParcelByDate(LocalDate date);
    void add();
    GetParcelDetailResponse getParcelDetail(int id);
    void updateParcelDetailRequest (UpdateParcelDetailRequest updateParcelDetailRequest); 
    GetTodayTaskForParcel getTodayTaskForParcels (int parcelId);
  
    
   
    

    

    
    
}
