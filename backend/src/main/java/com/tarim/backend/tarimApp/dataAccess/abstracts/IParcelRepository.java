package com.tarim.backend.tarimApp.dataAccess.abstracts;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.tarim.backend.tarimApp.core.entities.Parcel;

public interface IParcelRepository extends JpaRepository<Parcel, Integer> {
    boolean existsByparcelNumber(String name); //spring jpa key word

    /* 
    @Query(nativeQuery = true , value = "Select p.id, p.parcel_number, p.product_id, p.status  From parcels p WHERE p.status=TRUE")
    List<Parcel> findActiveParcels(); 
    */

    List<Parcel> findByStatusTrue();
    

    

    




    
}
