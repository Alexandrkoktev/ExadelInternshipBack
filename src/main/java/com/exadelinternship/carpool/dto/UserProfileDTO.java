package com.exadelinternship.carpool.dto;

public class UserProfileDTO {
    private String name;
    private String phoneNumber;
    private double ratingDriver;
    private double ratingPassenger;
    private String photoUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
