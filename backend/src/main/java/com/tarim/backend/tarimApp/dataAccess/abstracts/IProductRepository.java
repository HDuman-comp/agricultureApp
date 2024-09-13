package com.tarim.backend.tarimApp.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tarim.backend.tarimApp.core.entities.Product;

public interface IProductRepository  extends JpaRepository<Product,Integer>{
    boolean existsByproductName(String name);

    List <Product> findByIsActiveTrue(); 




    
}
