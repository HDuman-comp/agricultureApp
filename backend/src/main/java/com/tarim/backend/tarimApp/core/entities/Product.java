package com.tarim.backend.tarimApp.core.entities;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name =  "products")
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "productName")
    private String productName;

    @Column(name = "demandStartDate")
    private LocalDate demandStartDate;

    @Column(name = "demandEndDate")
    private LocalDate demandEndDate;

    @Column(name = "startDate")
    private LocalDate startDate;

    @Column(name = "growingTime")
    private int growingTime;

    @Column(name = "price")
    private double price;

    @Column(name = "imageUrl")
    private String imageUrl;

    @Column(name = "isActive")
    private boolean isActive;

    @ElementCollection
    @CollectionTable(name = "product_kademes", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "kademe")
    private List<Integer> kademe;
    @OneToMany (mappedBy = "product")
    private List<Parcel> parcels ;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductProcess> productProcesses;

    @OneToOne(mappedBy = "product")
    private ProductStatus productStatuses;


}
