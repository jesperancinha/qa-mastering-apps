package org.jesperancinha.car.lease.converters;

import org.jesperancinha.car.lease.dto.CarDto;
import org.jesperancinha.car.lease.model.Car;

import java.util.Objects;

public class CarConverter {
    public static CarDto toDto(Car car) {
        if(Objects.isNull(car)){
            return null;
        }
        return CarDto
                .builder()
                .id(car.getId())
                .version(car.getVersion())
                .model(car.getModel())
                .co2Emission(car.getCo2Emission())
                .make(car.getMake())
                .netPrice(car.getNetPrice())
                .numberDoors(car.getNumberDoors())
                .grossPrice(car.getGrossPrice())
                .millage(car.getMillage())
                .build();
    }

    public static Car toData(CarDto car) {
        return Car
                .builder()
                .id(car.getId())
                .version(car.getVersion())
                .model(car.getModel())
                .co2Emission(car.getCo2Emission())
                .make(car.getMake())
                .netPrice(car.getNetPrice())
                .numberDoors(car.getNumberDoors())
                .grossPrice(car.getGrossPrice())
                .millage(car.getMillage())
                .build();
    }
}
