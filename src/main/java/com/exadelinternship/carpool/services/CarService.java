package com.exadelinternship.carpool.services;

import com.exadelinternship.carpool.adapters.CarAdapter;
import com.exadelinternship.carpool.dto.CarDTO;
import com.exadelinternship.carpool.entity.Car;
import com.exadelinternship.carpool.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarAdapter carAdapter;

    public List<CarDTO> getAllCars(){
        List<CarDTO> result=new ArrayList<>();
        carRepository.findAll().forEach(car -> result.add(carAdapter.carToCarDto(car)));
        return result;
    }

    public CarDTO getCarById(long id){
        return carAdapter.carToCarDto(carRepository.getOne(id));
    }

    public void saveCar(CarDTO carDTO){
        Car car=carAdapter.carDtoToCar(carDTO);
        carRepository.save(car);
    }

    public void deleteCarById(long id){
        carRepository.deleteById(id);
    }
}
