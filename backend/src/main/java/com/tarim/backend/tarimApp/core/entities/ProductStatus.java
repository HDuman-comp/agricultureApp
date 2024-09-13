package com.tarim.backend.tarimApp.core.entities;

import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="productsStatus")
public class ProductStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    /* 
    @ElementCollection
    @CollectionTable(name = "product_status_days", joinColumns = @JoinColumn(name = "product_status_id"))
    @Column(name = "day")
    private List<Integer> days;
    */
    
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;


    @OneToOne
    @JoinColumn(name = "productStage_id")
    private ProductStage productStage;
    
    
    
    
}
