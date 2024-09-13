package com.tarim.backend.tarimApp.core.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "weather")
public class Weather {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "city")
    private String city;

    @Column(name = "date")
    private String date;

    @Column(name = "day")
    private String day;

    @Column(name = "icon")
    private String icon;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private String status;

    @Column(name = "degree")
    private Float degree;

    @Column(name = "min_degree")
    private Float minDegree;

    @Column(name = "max_degree")
    private Float maxDegree;

    @Column(name = "night_degree")
    private Float nightDegree;

    @Column(name = "humidity")
    private String humidity;

    @Column(name = "request_time")
    private Timestamp requestTime;
}
