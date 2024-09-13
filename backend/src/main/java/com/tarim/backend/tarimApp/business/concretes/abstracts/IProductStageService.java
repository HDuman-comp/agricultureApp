package com.tarim.backend.tarimApp.business.concretes.abstracts;

import java.time.LocalDate;
import java.util.List;

import com.tarim.backend.tarimApp.business.dto.request.ProductStage.CreateProductStageRequest;
import com.tarim.backend.tarimApp.business.dto.request.ProductStage.UpdateProductStageRequest;
import com.tarim.backend.tarimApp.business.dto.response.productStage.GetAllProductStageResponse;

public interface IProductStageService {
    List<GetAllProductStageResponse> getAll();
    void add(CreateProductStageRequest createProductStageRequest);
    void update (UpdateProductStageRequest updateProductStageRequest);
    void delete (int id);
    void updateParcelDailyStage(LocalDate localDate);
}
