package com.exadelinternship.carpool.adapters;

import com.exadelinternship.carpool.dto.CarDTO;
import com.exadelinternship.carpool.entity.Car;
import com.exadelinternship.carpool.entity.User;
import com.exadelinternship.carpool.repository.CarRepository;
import com.exadelinternship.carpool.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarAdapter {
    @Autowired
    UserRepository userRepository;
    public Car carDtoToCar(CarDTO carDTO){
        Car car=new Car();
        car.setCarInformation(carDTO.getCarInformation());
        car.setMaxSeats(carDTO.getCapacity());
        car.setId(carDTO.getId());
        car.setUser(userRepository.findById(carDTO.getUserId()).get());
        return car;
    }
    public CarDTO carToCarDto(Car car){
        CarDTO carDTO=new CarDTO();
        carDTO.setCapacity(car.getMaxSeats());
        carDTO.setCarInformation(car.getCarInformation());
        carDTO.setId(car.getId());
        carDTO.setUserId(car.getUser().getId());
        return carDTO;
    }

}