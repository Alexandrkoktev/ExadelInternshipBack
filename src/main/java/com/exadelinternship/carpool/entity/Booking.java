package com.exadelinternship.carpool.entity;

import org.springframework.data.annotation.Id;

public class Booking {
    @Id
    private long id;
    private ActiveRoute activeRoute;
    private User user;
    private double[] meetPoint;
    private double[] destinationPoint;

    public Booking(long id, ActiveRoute activeRoute, User user, double[] meetPoint, double[] destinationPoint) {
        this.id = id;
        this.activeRoute = activeRoute;
        this.user = user;
        this.meetPoint = meetPoint;
        this.destinationPoint = destinationPoint;
    }

    public long getId() {
        return id;
    }

    public ActiveRoute getActiveRoute() {
        return activeRoute;
    }

    public User getUser() {
        return user;
    }

    public double[] getMeetPoint() {
        return meetPoint;
    }

    public double[] getDestinationPoint() {
        return destinationPoint;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setActiveRoute(ActiveRoute activeRoute) {
        this.activeRoute = activeRoute;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setMeetPoint(double coordinateX, double coordinateY) {
        this.meetPoint = new double[2];
        this.meetPoint[0] = coordinateX;
        this.meetPoint[1] = coordinateY;
    }

    public void setDestinationPoint(double coordinateX, double coordinateY) {
        this.destinationPoint = new double[2];
        this.destinationPoint[0] = coordinateX;
        this.destinationPoint[1] = coordinateY;

    }
}
