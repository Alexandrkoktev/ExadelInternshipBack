package com.exadelinternship.carpool.repository;

import com.exadelinternship.carpool.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Long> {
    Set<Booking> getByUser_Id(Long id);
}
