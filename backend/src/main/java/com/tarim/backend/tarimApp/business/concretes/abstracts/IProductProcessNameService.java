package com.tarim.backend.tarimApp.business.concretes.abstracts;

import java.util.List;

import com.tarim.backend.tarimApp.business.dto.request.ProductProcessName.CreateProductProcessNameRequest;
import com.tarim.backend.tarimApp.business.dto.request.ProductProcessName.UpdateProductProcessNameRequest;
import com.tarim.backend.tarimApp.business.dto.response.productProcessName.GetAllProductProcessNameResponse;

public interface IProductProcessNameService {
    
    List<GetAllProductProcessNameResponse> getAll();
    void add(CreateProductProcessNameRequest createProductProcessNameRequest);
    void update(UpdateProductProcessNameRequest updateProductProcessNameRequest);
    void delete (int id);

}
