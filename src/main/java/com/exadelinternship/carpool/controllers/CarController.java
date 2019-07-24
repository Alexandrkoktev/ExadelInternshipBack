package com.exadelinternship.carpool.controllers;

import com.exadelinternship.carpool.dto.CarDTO;
import com.exadelinternship.carpool.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins ="*", allowedHeaders = "*")
public class CarController {
    @Autowired
    private CarService carService;

    @GetMapping("/profile/cars")
    @ResponseBody
    public List<CarDTO> getAllCarProfile(){
        return  carService.getAllCars();
    }

    @GetMapping("/newRoute/cars")
    @ResponseBody
    public List<CarDTO> getAllCarRoute(){
        return  carService.getAllCars();
    }

    @PostMapping("/profile/cars")
    @ResponseBody
    public void addCar(@Valid @RequestBody CarDTO carDTO){
        carService.saveCar(carDTO);
    }

    @PutMapping("/profile/cars/{id}")
    @ResponseBody
    public void updateCar(@Valid @RequestBody CarDTO carDTO){
        carService.saveCar(carDTO);
    }

    @DeleteMapping("/profile/cars/{id}")
    @ResponseBody
    public void deleteCar(@PathVariable("id") long id){
        carService.deleteCarById(id);
    }




}
