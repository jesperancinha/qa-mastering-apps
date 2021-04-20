package org.jesperancinha.car.lease.dto;

import lombok.Data;

@Data
public class CarDto {
    private Long id;
    private String make;
    private String model;
    private String version;
    private Long numberDoors;
    private Long co2Emission;
    private Long grossPrice;
    private Long netPrice;
}
