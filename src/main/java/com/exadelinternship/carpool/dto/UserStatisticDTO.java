package com.exadelinternship.carpool.dto;

public class UserStatisticDTO {
    private long id;
    private String name;
    private double ratingDriver;
    private double ratingPassenger;
    private double distance;
    private int amountOfPassengers;
    private int amountOfBookings;
    private int amountOfRoutes;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRatingDriver() {
        return ratingDriver;
    }

    public void setRatingDriver(double ratingDriver) {
        this.ratingDriver = ratingDriver;
    }

    public double getRatingPassenger() {
        return ratingPassenger;
    }

    public void setRatingPassenger(double ratingPassenger) {
        this.ratingPassenger = ratingPassenger;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getAmountOfPassengers() {
        return amountOfPassengers;
    }

    public void setAmountOfPassengers(int amountOfPassengers) {
        this.amountOfPassengers = amountOfPassengers;
    }

    public int getAmountOfBookings() {
        return amountOfBookings;
    }

    public void setAmountOfBookings(int amountOfBookings) {
        this.amountOfBookings = amountOfBookings;
    }

    public int getAmountOfRoutes() {
        return amountOfRoutes;
    }

    public void setAmountOfRoutes(int amountOfRoutes) {
        this.amountOfRoutes = amountOfRoutes;
    }
}
