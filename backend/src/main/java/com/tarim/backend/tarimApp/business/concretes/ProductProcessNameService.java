package com.tarim.backend.tarimApp.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tarim.backend.tarimApp.business.concretes.abstracts.IProductProcessNameService;
import com.tarim.backend.tarimApp.business.dto.request.ProductProcessName.CreateProductProcessNameRequest;
import com.tarim.backend.tarimApp.business.dto.request.ProductProcessName.UpdateProductProcessNameRequest;
import com.tarim.backend.tarimApp.business.dto.response.productProcessName.GetAllProductProcessNameResponse;
import com.tarim.backend.tarimApp.business.mapper.ModelMapperService;
import com.tarim.backend.tarimApp.core.entities.ProductProcessName;
import com.tarim.backend.tarimApp.dataAccess.abstracts.IProductProcessNameRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductProcessNameService implements IProductProcessNameService {
   
    private IProductProcessNameRepository productProcessNameRepository;
    private ModelMapperService modelMapperService;


    @Override

    public List<GetAllProductProcessNameResponse> getAll() {
        List<ProductProcessName> productProcessNames = productProcessNameRepository.findAll();
        List<GetAllProductProcessNameResponse> getAllProductProcessNameResponses = productProcessNames.stream()
        .map(productProcessName -> this.modelMapperService.forResponse()
        .map(productProcessName, GetAllProductProcessNameResponse.class)).collect(Collectors.toList());
        return getAllProductProcessNameResponses;
    }

    @Override
    public void add(CreateProductProcessNameRequest createProductProcessNameRequest) {
        ProductProcessName productProcessName=this.modelMapperService.forRequest().map(createProductProcessNameRequest, ProductProcessName.class);
        productProcessNameRepository.save(productProcessName);
    }

    @Override
    public void update(UpdateProductProcessNameRequest updateProductProcessNameRequest) {
        ProductProcessName productProcessName = this.modelMapperService.forRequest().map(updateProductProcessNameRequest, ProductProcessName.class);
        productProcessNameRepository.save(productProcessName);
    }


    @Override
    public void delete(int id) {
        productProcessNameRepository.deleteById(id);
    }

    
}
