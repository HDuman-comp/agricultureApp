package com.tarim.backend.tarimApp.business.concretes.abstracts;

import java.util.List;

import com.tarim.backend.tarimApp.business.dto.request.ProductProcess.CreateProductProcessRequest;
import com.tarim.backend.tarimApp.business.dto.request.ProductProcess.UpdateProductProcessRequest;
import com.tarim.backend.tarimApp.business.dto.response.productProcess.GetAllProductProcessResponse;


public interface IProductProcessService {

    List<GetAllProductProcessResponse> getAll();
    void add(CreateProductProcessRequest createProductProcessRequest);
    void update(UpdateProductProcessRequest  updateProductProcessRequest);
    void delete(int id);

    
    
    
}
