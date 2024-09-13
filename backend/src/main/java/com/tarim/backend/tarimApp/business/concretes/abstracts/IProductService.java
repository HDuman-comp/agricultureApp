package com.tarim.backend.tarimApp.business.concretes.abstracts;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.tarim.backend.tarimApp.business.dto.request.Product.CreateProductRequest;
import com.tarim.backend.tarimApp.business.dto.request.Product.CreateProductWithProcessRequest;
import com.tarim.backend.tarimApp.business.dto.request.Product.UpdateProductRequest;
import com.tarim.backend.tarimApp.business.dto.response.product.FindActiveProductResponse;
import com.tarim.backend.tarimApp.business.dto.response.product.GetAllProductPageResponse;
import com.tarim.backend.tarimApp.business.dto.response.product.GetAllProductResponse;
import com.tarim.backend.tarimApp.business.dto.response.product.GetAllProductWithProcessResponse;
import com.tarim.backend.tarimApp.business.dto.response.product.GetProductDetailResponse;
import com.tarim.backend.tarimApp.core.entities.Product;

public interface IProductService {

    List<GetAllProductResponse> getAll();
    void add (CreateProductRequest createProductRequest);
    void update (UpdateProductRequest updateProductRequest);
    void delete (int id);

    List<GetAllProductPageResponse> getAllProductPage();
    List<GetAllProductWithProcessResponse> getAllProductWithProcess();
    void addProduct (CreateProductWithProcessRequest createProductWithProcessRequest);
    String addProductImage (MultipartFile multipartFile);
    void updateProductIsActive();
    void addProductIsActive(Product product);
    List<FindActiveProductResponse> findActiveProduct();
    GetProductDetailResponse getProductDetail(int id);
    
    
    
}
