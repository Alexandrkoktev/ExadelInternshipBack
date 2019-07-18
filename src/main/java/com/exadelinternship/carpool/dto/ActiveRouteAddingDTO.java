package com.exadelinternship.carpool.dto;

import com.exadelinternship.carpool.entity.Car;

import java.sql.Timestamp;

public class ActiveRouteAddingDTO {
    private Timestamp timeAndDate;
    private short maxSeats;
    private short freeSeats;
    private Car car;
}
