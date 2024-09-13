package com.tarim.backend.tarimApp.webApi.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tarim.backend.tarimApp.business.concretes.ProductProcessNameService;
import com.tarim.backend.tarimApp.business.dto.request.ProductProcessName.CreateProductProcessNameRequest;
import com.tarim.backend.tarimApp.business.dto.request.ProductProcessName.UpdateProductProcessNameRequest;
import com.tarim.backend.tarimApp.business.dto.response.productProcessName.GetAllProductProcessNameResponse;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@AllArgsConstructor
@RequestMapping("/api/productProcessName")
public class ProductProcessNameController {

    private ProductProcessNameService productProcessNameService;

    @GetMapping("/getAll")
    public List<GetAllProductProcessNameResponse> getAll(){
        return this.productProcessNameService.getAll();
    }

    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add (@RequestBody CreateProductProcessNameRequest createProductProcessNameRequest){
        this.productProcessNameService.add(createProductProcessNameRequest);
    }


    @PutMapping("update")
    public void update(UpdateProductProcessNameRequest updateProductProcessNameRequest){
        this.productProcessNameService.update(updateProductProcessNameRequest);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id){
        this.productProcessNameService.delete(id);
    }
    
}
