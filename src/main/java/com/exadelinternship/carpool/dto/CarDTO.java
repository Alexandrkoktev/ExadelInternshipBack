package com.exadelinternship.carpool.dto;

public class CarDTO {
    private long id;
    private String carInformation;
    private int capacity;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCarInformation() {
        return carInformation;
    }

    public void setCarInformation(String carInformation) {
        this.carInformation = carInformation;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
