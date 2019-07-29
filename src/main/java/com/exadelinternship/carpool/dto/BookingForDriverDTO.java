package com.exadelinternship.carpool.dto;

public class BookingForDriverDTO {
    private long bookingId;
    private String name;
    private String phoneNumber;
    private double[] meetPoint;
    private double[] destinationPoint;
    private double rating;

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

    public double[] getMeetPoint() {
        return meetPoint;
    }

    public void setMeetPoint(double[] meetPoint) {
        this.meetPoint = meetPoint;
    }

    public double[] getDestinationPoint() {
        return destinationPoint;
    }

    public void setDestinationPoint(double[] destinationPoint) {
        this.destinationPoint = destinationPoint;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public long getBookingId() {
        return bookingId;
    }

    public void setBookingId(long bookingId) {
        this.bookingId = bookingId;
    }
}
