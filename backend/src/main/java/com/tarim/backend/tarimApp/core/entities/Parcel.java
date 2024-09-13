package com.tarim.backend.tarimApp.core.entities;


import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Parcels")
public class Parcel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name="parcelNumber")
    private String parcelNumber;

    @Column(name = "status")
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToMany(mappedBy = "parcel", cascade =  CascadeType.ALL)
    private List<Task> tasks;

    @OneToMany(mappedBy = "parcel", cascade = CascadeType.ALL)
    @Column(name="dateWithImage")
    List<ParcelImage> parcelImages;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;




    
    
    
}
