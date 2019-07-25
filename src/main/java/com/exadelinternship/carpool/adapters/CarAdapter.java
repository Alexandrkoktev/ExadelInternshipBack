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

    public Car carDtoToCar(CarDTO carDTO,long userId){
        Car car=new Car();
        car.setCarInformation(carDTO.getCarInformation());
        car.setId(carDTO.getId());
        car.setUser(userRepository.getOne(userId));
        return car;
    }

    public CarDTO carToCarDto(Car car){
        CarDTO carDTO=new CarDTO();
        carDTO.setCarInformation(car.getCarInformation());
        carDTO.setId(car.getId());
        return carDTO;
    }

}
