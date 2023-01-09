package org.jesperancinha.car.lease.model;

import lombok.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Data
@Entity
@Getter
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
