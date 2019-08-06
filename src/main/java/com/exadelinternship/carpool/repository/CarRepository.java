package com.exadelinternship.carpool.repository;

import com.exadelinternship.carpool.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    Set<Car> findAllByUser_Id(long id);
    Set<Car> findAllByUser_IdAndDeleted(long id, boolean deleted);
}
