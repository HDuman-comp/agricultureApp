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

import com.tarim.backend.tarimApp.business.concretes.abstracts.IProductProcessService;
import com.tarim.backend.tarimApp.business.dto.request.ProductProcess.CreateProductProcessRequest;
import com.tarim.backend.tarimApp.business.dto.request.ProductProcess.UpdateProductProcessRequest;
import com.tarim.backend.tarimApp.business.dto.response.productProcess.GetAllProductProcessResponse;


import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/productProcess")
@AllArgsConstructor
public class ProductProcessController {
    private IProductProcessService productProcessService;

    @GetMapping("/getAll")
    public List<GetAllProductProcessResponse> getAll(){
        return this.productProcessService.getAll();
    }

    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(@RequestBody CreateProductProcessRequest createProductProcessRequest){
        this.productProcessService.add(createProductProcessRequest);
    }

    @PutMapping("/update")
    public void update(UpdateProductProcessRequest updateProductProcessRequest){
        this.productProcessService.update(updateProductProcessRequest);
    } 

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id){
        this.productProcessService.delete(id);
    }
}
