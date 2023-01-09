package org.jesperancinha.car.lease.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {
    private Long id;
    private String make;
    private String model;
    private String version;
    private Long numberDoors;
    private Long co2Emission;
    private Long grossPrice;
    private Long netPrice;
    private Long millage;
}
