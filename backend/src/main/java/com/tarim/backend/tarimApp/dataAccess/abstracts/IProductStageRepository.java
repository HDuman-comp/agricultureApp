package com.tarim.backend.tarimApp.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tarim.backend.tarimApp.core.entities.ProductStage;

public interface IProductStageRepository  extends JpaRepository<ProductStage,Integer>{
    
}
