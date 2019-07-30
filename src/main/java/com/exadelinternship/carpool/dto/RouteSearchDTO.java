package com.exadelinternship.carpool.dto;

import java.sql.Timestamp;

public class RouteSearchDTO {
    private double[] meetPoint;
    private double[] destinationPoint;
    private Timestamp datetime;

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

    public Timestamp getDatetime() {
        return datetime;
    }

    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
    }
}
