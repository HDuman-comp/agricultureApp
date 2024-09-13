package com.tarim.backend.tarimApp.business.rules;

import org.springframework.stereotype.Service;

import com.tarim.backend.tarimApp.business.exception.BusinessException;
import com.tarim.backend.tarimApp.dataAccess.abstracts.IProductRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductBusinessRules {

    private IProductRepository productRepository;

    public void checkIfParcelNumberExists(String name){
        if(this.productRepository.existsByproductName(name)){
            throw new BusinessException("Product name already exists"); //Java Exception types
        }
    }
}
