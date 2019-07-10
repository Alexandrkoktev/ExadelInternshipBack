package com.exadelinternship.carpool.repository;

import com.exadelinternship.carpool.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
