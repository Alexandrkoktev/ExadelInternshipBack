package com.exadelinternship.carpool.repository;

import com.exadelinternship.carpool.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BokkingRepository extends JpaRepository<Booking,Long> {
}
