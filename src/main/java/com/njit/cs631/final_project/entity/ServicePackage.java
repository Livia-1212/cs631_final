package com.njit.cs631.final_project.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ServicePackage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_package_id")
    private int packageId;

    @Column(name = "package_type")
    private String packageName;
//    private int carAge;
    @Column(name = "estimated_cost")
    private BigDecimal laborCost;
    private int estimatedHours;
    private String description;
}
