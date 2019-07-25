package com.exadelinternship.carpool.dto;

public class CarDTO {
    private long id;
    private String carInformation;

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

    @Override
    public String toString() {
        return super.toString();
    }
}
