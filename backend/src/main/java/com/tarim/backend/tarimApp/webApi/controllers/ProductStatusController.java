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

import com.tarim.backend.tarimApp.business.concretes.abstracts.IProductStatusService;
import com.tarim.backend.tarimApp.business.dto.request.ProductStatus.CreateProductStatusRequest;
import com.tarim.backend.tarimApp.business.dto.request.ProductStatus.UpdateProductStatusRequest;
import com.tarim.backend.tarimApp.business.dto.response.productStatus.GetAllProductStatusResponse;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/productStatus")
@AllArgsConstructor
public class ProductStatusController {
    
    private IProductStatusService productStatusService;

    @GetMapping("/getAll")
    public List<GetAllProductStatusResponse> getAll(){
        return this.productStatusService.getAll();
    }

    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add (@RequestBody CreateProductStatusRequest createProductStatusRequest){
        this.productStatusService.add(createProductStatusRequest);
    }

    @PutMapping("/update")
    public void update(UpdateProductStatusRequest updateProductStatusRequest){
        this.productStatusService.update(updateProductStatusRequest);
    }

    @DeleteMapping("/delete/{id}")
    public void delete (@PathVariable int id){
        this.productStatusService.delete(id);
    }
}
