package com.tarim.backend.tarimApp.webApi.controllers;

import java.util.List;


import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestBody;


import com.tarim.backend.tarimApp.business.concretes.ProductService;
import com.tarim.backend.tarimApp.business.dto.request.Product.CreateProductRequest;
import com.tarim.backend.tarimApp.business.dto.request.Product.CreateProductWithProcessRequest;
import com.tarim.backend.tarimApp.business.dto.request.Product.UpdateProductRequest;
import com.tarim.backend.tarimApp.business.dto.response.product.FindActiveProductResponse;
import com.tarim.backend.tarimApp.business.dto.response.product.GetAllProductPageResponse;
import com.tarim.backend.tarimApp.business.dto.response.product.GetAllProductResponse;
import com.tarim.backend.tarimApp.business.dto.response.product.GetAllProductWithProcessResponse;
import com.tarim.backend.tarimApp.business.dto.response.product.GetProductDetailResponse;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;



@RestController
@AllArgsConstructor
@RequestMapping("/api/products")
public class ProductController {
    
    private ProductService productService;


    @GetMapping("/getAll")
    public List<GetAllProductResponse> getAll(){
        return this.productService.getAll();
    }

    @GetMapping("/getAllProductWithProcess")
    public List<GetAllProductWithProcessResponse> getAllProductWithProcessResponses (){
        return this.productService.getAllProductWithProcess();
    }

    @GetMapping("getProductDetail/{id}")
    public GetProductDetailResponse productDetailResponse (int id){
        return this.productService.getProductDetail(id);
    }

    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(@Valid @RequestBody CreateProductRequest createProductRequest) {
        this.productService.add(createProductRequest);
    }

    
    @PostMapping(path = "/addProduct")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void addProduct(@Valid @RequestBody() CreateProductWithProcessRequest createProductWithProcessRequest){
        this.productService.addProduct(createProductWithProcessRequest);
    }

    @PostMapping(path = "/addProductImage", consumes = {"multipart/form-data"} )
    @ResponseStatus(code = HttpStatus.CREATED)
    public String addProductImage (@RequestPart MultipartFile multipartFile){
        return this.productService.addProductImage(multipartFile);
    }
    


    @PutMapping("/update")
    public void update(UpdateProductRequest updateProductRequest){
        this.productService.update(updateProductRequest);
    }

    @DeleteMapping("/delete/{id}")
    public void delete (int id){
        this.productService.delete(id);
    }

    @GetMapping("/getAllProductPageList")
    public List<GetAllProductPageResponse> getAllProduct(){
        return this.productService.getAllProductPage();
    }

    @GetMapping("/findActiveProduct")
    public List<FindActiveProductResponse> findActiveProduct (){
        return this.productService.findActiveProduct();
    }

}
