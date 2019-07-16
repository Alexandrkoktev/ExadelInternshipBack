package com.exadelinternship.carpool.services;

import com.exadelinternship.carpool.adapters.CarAdapter;
import com.exadelinternship.carpool.dto.CarDTO;
import com.exadelinternship.carpool.entity.Car;
import com.exadelinternship.carpool.entity.User;
import com.exadelinternship.carpool.repository.CarRepository;
import com.exadelinternship.carpool.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CarAdapter carAdapter;

    public List<CarDTO> getAllCars(){
        List<CarDTO> result=new ArrayList<>();
        carRepository.findAll().forEach(car -> result.add(carAdapter.carToCarDto(car)));
        return result;
    }

    public void saveCar(CarDTO carDTO){
        Car car=carAdapter.carDtoToCar(carDTO);
        System.out.println("Info "+carDTO.getCarInformation());
        carRepository.save(car);
    }

    public void deleteById(long id){
        carRepository.deleteById(id);
    }
}
