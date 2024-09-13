package com.tarim.backend.tarimApp.business.concretes.abstracts;

import java.util.List;

import com.tarim.backend.tarimApp.business.dto.request.ProductStatus.CreateProductStatusRequest;
import com.tarim.backend.tarimApp.business.dto.request.ProductStatus.UpdateProductStatusRequest;
import com.tarim.backend.tarimApp.business.dto.response.productStatus.GetAllProductStatusResponse;

public interface IProductStatusService {
   List<GetAllProductStatusResponse>  getAll();
   void add(CreateProductStatusRequest createProductStatusRequest);
   void update (UpdateProductStatusRequest updateProductStatusRequest);
   void delete (int id);
   
}
