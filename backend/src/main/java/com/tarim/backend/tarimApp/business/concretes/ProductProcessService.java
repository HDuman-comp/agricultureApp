package com.tarim.backend.tarimApp.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tarim.backend.tarimApp.business.concretes.abstracts.IProductProcessService;
import com.tarim.backend.tarimApp.business.dto.request.ProductProcess.CreateProductProcessRequest;
import com.tarim.backend.tarimApp.business.dto.request.ProductProcess.UpdateProductProcessRequest;
import com.tarim.backend.tarimApp.business.dto.response.productProcess.GetAllProductProcessResponse;
import com.tarim.backend.tarimApp.business.mapper.ModelMapperService;
import com.tarim.backend.tarimApp.core.entities.ProductProcess;
import com.tarim.backend.tarimApp.dataAccess.abstracts.IProductProcessRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductProcessService implements IProductProcessService {
    
    private IProductProcessRepository productProcessRepository;
    private ModelMapperService modelMapperService;

    

    
    @Override
    public List<GetAllProductProcessResponse> getAll() {
        List<ProductProcess> productProcesses= productProcessRepository.findAll();
        List<GetAllProductProcessResponse> getAllProductProcessResponses= productProcesses.stream()
        .map(productProcess -> this.modelMapperService.forResponse()
        .map(productProcess, GetAllProductProcessResponse.class)).collect(Collectors.toList() );
        return getAllProductProcessResponses;

    }




    @Override
    public void add(CreateProductProcessRequest createProductProcessRequest) {
        ProductProcess productProcess= this.modelMapperService.forRequest().map(createProductProcessRequest, ProductProcess.class);
        productProcessRepository.save(productProcess);
               }

    @Override
    public void update(UpdateProductProcessRequest updateProductProcessRequest) {
        ProductProcess productProcess = this.modelMapperService.forRequest().map(updateProductProcessRequest,ProductProcess.class);
        productProcessRepository.save(productProcess);
    }



    @Override
    public void delete(int id) {
        productProcessRepository.deleteById(id);
    }




    
    

}
