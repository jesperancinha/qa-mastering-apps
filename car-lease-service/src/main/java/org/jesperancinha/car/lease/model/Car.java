package org.jesperancinha.car.lease.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String make;

    @Column
    private String model;

    @Column
    private String version;

    @Column
    private Long numberDoors;

    @Column
    private Long co2Emission;

    @Column
    private Long grossPrice;

    @Column
    private Long netPrice;

    @Column
    private Long millage;
}
