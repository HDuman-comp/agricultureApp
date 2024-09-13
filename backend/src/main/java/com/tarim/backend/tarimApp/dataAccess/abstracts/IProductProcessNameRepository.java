package com.tarim.backend.tarimApp.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tarim.backend.tarimApp.core.entities.ProductProcessName;

public interface IProductProcessNameRepository extends JpaRepository<ProductProcessName,Integer>{
    
}
