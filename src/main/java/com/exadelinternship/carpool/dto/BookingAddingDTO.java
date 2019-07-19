package com.exadelinternship.carpool.dto;

public class BookingAddingDTO {
    private long activeRouteId;
    private double[] meetPoint;
    private double[] destinationPoint;

    public long getActiveRouteId() {
        return activeRouteId;
    }

    public void setActiveRouteId(long activeRouteId) {
        this.activeRouteId = activeRouteId;
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

}
