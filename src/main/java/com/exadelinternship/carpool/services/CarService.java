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
        carRepository.findAllByUser_IdAndDeleted(userId,false).forEach(car -> result.add(carAdapter.carToCarDto(car)));
        return result;
    }

    @Transactional
    public void saveCar(String carInfo) throws Exception{
        if(carInfo.length()<256) {
            long userId = ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
            Car car = carAdapter.carInfoToCar(carInfo, userId);
            carRepository.save(car);
        }
        else{
            throw new Exception();
        }
    }

    @Transactional
    public void editCar(CarDTO carDTO) throws Exception{
        if(carDTO.getCarInformation().length()<256) {
            long userId = ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
            Car car = carAdapter.carDtoToCar(carDTO, userId);
            carRepository.save(car);
        }
        else{
            throw new Exception();
        }
    }
    @Transactional
    public void deleteCarById(long id){
        long userId=((UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        Car car=carRepository.getOne(id);
        if(car.getUser().getId()==userId) {
            car.setDeleted(true);
            carRepository.save(car);
        }

    }
}
