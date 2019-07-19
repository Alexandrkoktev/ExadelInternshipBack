package com.exadelinternship.carpool.services;

import com.exadelinternship.carpool.adapters.CarAdapter;
import com.exadelinternship.carpool.dto.CarDTO;
import com.exadelinternship.carpool.entity.Car;
import com.exadelinternship.carpool.entity.impl.UserDetailsImpl;
import com.exadelinternship.carpool.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
        long userId=((UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        carRepository.findAllByUser_Id(userId).forEach(car -> result.add(carAdapter.carToCarDto(car)));
        return result;
    }

    @Transactional
    public void saveCar(CarDTO carDTO){
        long userId=((UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        Car car=carAdapter.carDtoToCar(carDTO,userId);
        carRepository.save(car);
    }

    @Transactional
    public void deleteCarById(long id){
        long userId=((UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        Car car=carRepository.getOne(id);
        if(car.getUser().getId()==userId) {
            carRepository.deleteById(id);
        }

    }
}
