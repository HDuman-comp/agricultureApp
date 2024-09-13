package com.tarim.backend.tarimApp.webApi.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tarim.backend.tarimApp.business.concretes.abstracts.IProductStageService;
import com.tarim.backend.tarimApp.business.dto.request.ProductStage.CreateProductStageRequest;
import com.tarim.backend.tarimApp.business.dto.request.ProductStage.UpdateProductStageRequest;
import com.tarim.backend.tarimApp.business.dto.response.productStage.GetAllProductStageResponse;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/productStage")
public class ProductStageController {
    
    private IProductStageService productStageService;

    @GetMapping("/getAll")
    public List<GetAllProductStageResponse> getAll(){
        return this.productStageService.getAll();
    }

    @PostMapping("/add")
    @ResponseStatus(code=HttpStatus.CREATED)
    public void add(@RequestBody CreateProductStageRequest createProductStageRequest){
        this.productStageService.add(createProductStageRequest);
    }

    @PutMapping("/update")
    public void update(UpdateProductStageRequest updateProductStageRequest){
        this.productStageService.update(updateProductStageRequest);

    }

    @DeleteMapping("/delete/{id}")
    public void delete (@PathVariable int id ){
        this.productStageService.delete(id);
    }


}
