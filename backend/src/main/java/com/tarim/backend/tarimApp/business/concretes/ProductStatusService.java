package com.tarim.backend.tarimApp.business.concretes;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tarim.backend.tarimApp.business.concretes.abstracts.IProductStatusService;
import com.tarim.backend.tarimApp.business.dto.request.ProductStatus.CreateProductStatusRequest;
import com.tarim.backend.tarimApp.business.dto.request.ProductStatus.UpdateProductStatusRequest;
import com.tarim.backend.tarimApp.business.dto.response.productStatus.GetAllProductStatusResponse;
import com.tarim.backend.tarimApp.business.mapper.ModelMapperService;
import com.tarim.backend.tarimApp.core.entities.ProductStatus;
import com.tarim.backend.tarimApp.dataAccess.abstracts.IProductStatusRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductStatusService implements IProductStatusService {

    private IProductStatusRepository productStatusRepository;
    private ModelMapperService modelMapperService;

    @Override
    public List<GetAllProductStatusResponse> getAll() {
        
        List<ProductStatus> productStatus = productStatusRepository.findAll();
        List<GetAllProductStatusResponse> getAllProductStatusResponses = productStatus.stream()
        .map(productStatu -> this.modelMapperService.forResponse()
        .map(productStatu, GetAllProductStatusResponse.class)).collect(Collectors.toList());
        return getAllProductStatusResponses;

    }
    @Override
    public void add(CreateProductStatusRequest createProductStatusRequest) {
        ProductStatus productStatus = this.modelMapperService.forRequest().map(createProductStatusRequest, ProductStatus.class); 
        this.productStatusRepository.save(productStatus);
       
    }

    @Override
    public void update(UpdateProductStatusRequest updateProductStatusRequest) {
        ProductStatus productStatus = this.modelMapperService.forRequest().map(updateProductStatusRequest, ProductStatus.class);
        this.productStatusRepository.save(productStatus);
    }
    @Override
    public void delete(int id) {
        this.productStatusRepository.deleteById(id);
    }



    
}
