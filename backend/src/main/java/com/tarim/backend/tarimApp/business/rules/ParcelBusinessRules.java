package com.tarim.backend.tarimApp.business.rules;

import org.springframework.stereotype.Service;

import com.tarim.backend.tarimApp.business.exception.BusinessException;
import com.tarim.backend.tarimApp.dataAccess.abstracts.IParcelRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ParcelBusinessRules {
    
    private IParcelRepository parcelRepository;

    public void checkIfParcelNumberExists(String name){
        if(this.parcelRepository.existsByparcelNumber(name)){
            throw new BusinessException("Parsel number already exists"); //Java Exception types
        }
    }
    
}
