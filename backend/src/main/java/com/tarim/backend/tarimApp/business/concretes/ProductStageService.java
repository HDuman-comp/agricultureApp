package com.tarim.backend.tarimApp.business.concretes;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tarim.backend.tarimApp.business.concretes.abstracts.IProductStageService;
import com.tarim.backend.tarimApp.business.dto.request.ProductStage.CreateProductStageRequest;
import com.tarim.backend.tarimApp.business.dto.request.ProductStage.UpdateProductStageRequest;
import com.tarim.backend.tarimApp.business.dto.response.productStage.GetAllProductStageResponse;
import com.tarim.backend.tarimApp.business.mapper.ModelMapperService;
import com.tarim.backend.tarimApp.core.entities.ProductStage;
import com.tarim.backend.tarimApp.dataAccess.abstracts.IProductStageRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductStageService implements IProductStageService {
    
    private IProductStageRepository productStageRepository;
    private ModelMapperService modelMapperService;

    @Override
    public List<GetAllProductStageResponse> getAll() {
        List<ProductStage> productStages = productStageRepository.findAll();
        List<GetAllProductStageResponse> getAllProductStageResponses = productStages.stream()
        .map(productStage -> this.modelMapperService.forResponse()
        .map(productStage, GetAllProductStageResponse.class)).collect(Collectors.toList());
        return getAllProductStageResponses;
    }

    @Override
    public void add(CreateProductStageRequest createProductStageRequest) {
        ProductStage productStage = this.modelMapperService.forRequest().map(createProductStageRequest, ProductStage.class);
        this.productStageRepository.save(productStage);

    }


    @Override
    public void update(UpdateProductStageRequest updateProductStageRequest) {
        ProductStage  productStage=this.modelMapperService.forRequest().map(updateProductStageRequest, ProductStage.class);
        this.productStageRepository.save(productStage);
    }

    @Override
    public void delete(int id) {
        this.productStageRepository.deleteById(id);
    }

    @Override
    public void updateParcelDailyStage(LocalDate localDate) {

    }

    
}
