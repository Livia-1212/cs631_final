package com.njit.cs631.final_project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="vehicle_id")
    private Long vehicleID;

    private String make;
    private String model;
    private int year;
    private String vin;
    private BigDecimal price;
    private BigDecimal costPrice;
    @Column(name="is_sold")
    private boolean soldStatus;
}

