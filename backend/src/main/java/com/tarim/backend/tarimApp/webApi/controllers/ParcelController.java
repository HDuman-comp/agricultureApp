package com.tarim.backend.tarimApp.webApi.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tarim.backend.tarimApp.business.concretes.abstracts.IParcelService;
import com.tarim.backend.tarimApp.business.dto.request.Parcel.UpdateParcelDetailRequest;
import com.tarim.backend.tarimApp.business.dto.request.Parcel.UpdateParcelRequest;
import com.tarim.backend.tarimApp.business.dto.response.parcel.GetAllParcelByDateResponse;
import com.tarim.backend.tarimApp.business.dto.response.parcel.GetAllParcelResponse;
import com.tarim.backend.tarimApp.business.dto.response.parcel.GetParcelDetailResponse;
import com.tarim.backend.tarimApp.business.dto.response.parcel.GetTodayTaskForParcel;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/parcels")
@AllArgsConstructor
public class ParcelController {
    
    private IParcelService parcelService;

    @GetMapping("/getAll")
    public List<GetAllParcelResponse> getAll(){

        return this.parcelService.getAll();
    }

    @GetMapping("getParcelDetail/{id}")
    public GetParcelDetailResponse getParcelDetail(@PathVariable int id){
            return this.parcelService.getParcelDetail(id);
        
        }
    /* 
    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add (@Valid() @RequestBody CreateParcelRequest createParcelRequest){
        this.parcelService.add(createParcelRequest);
    }
    */

    @PostMapping("/addParcel")
    public void addParcel(){
        this.parcelService.add();
    }



    @PutMapping("/update")
    public void update(UpdateParcelRequest updateParcelRequest){
        this.parcelService.update(updateParcelRequest);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id){
        this.parcelService.delete(id);
    }

    @GetMapping("/GetAllParcelByDate/{date}")
    public List<GetAllParcelByDateResponse> GetAllParcelByDate(LocalDate date){
        return this.parcelService.GetAllParcelByDate(date);
    }

    @PostMapping("UpdateParcelDetail")

    public void updateParcelDetailRequest (UpdateParcelDetailRequest updateParcelDetailRequest){
        this.parcelService.updateParcelDetailRequest(updateParcelDetailRequest);
    } 

    @GetMapping("/getTodayTaskForParcel/{parcelId}")
    public GetTodayTaskForParcel getTodayTaskForParcel (int parcelId){
        return this.parcelService.getTodayTaskForParcels(parcelId);
    }



}
