package com.exadelinternship.carpool.entity;


import org.springframework.data.annotation.Id;

import java.sql.Date;
import java.sql.Time;

public class ActiveRoute {
    @Id
    private long id;
    private Date date;
    private Time time;
    private Route route;
    private Car car;
    private short maxSeats;
    private short freeSeats;

    public long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }

    public Route getRoute() {
        return route;
    }

    public Car getCar() {
        return car;
    }

    public short getMaxSeats() {
        return maxSeats;
    }

    public short getFreeSeats() {
        return freeSeats;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public void setMaxSeats(short maxSeats) {
        this.maxSeats = maxSeats;
    }

    public void setFreeSeats(short freeSeats) {
        this.freeSeats = freeSeats;
    }
}
