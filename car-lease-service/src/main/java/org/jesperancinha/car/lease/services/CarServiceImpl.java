package org.jesperancinha.car.lease.services;

import org.jesperancinha.car.lease.converters.CarConverter;
import org.jesperancinha.car.lease.dto.CarDto;
import org.jesperancinha.car.lease.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public CarDto createCar(CarDto carDto) {
        return CarConverter.toDto(carRepository.save(CarConverter.toData(carDto)));
    }

    @Override
    public CarDto getCarById(Long id) {
        return CarConverter.toDto(carRepository.findById(id).orElse(null));
    }

    @Override
    public CarDto updateCar(CarDto carDto) {
        return CarConverter.toDto(carRepository.save(CarConverter.toData(carDto)));
    }

    @Override
    public void deleteCarById(Long id) {
        carRepository.deleteById(id);
    }

    @Override
    public List<CarDto> getAll() {
        return carRepository.findAll().stream().map(CarConverter::toDto).collect(Collectors.toList());
    }
}
