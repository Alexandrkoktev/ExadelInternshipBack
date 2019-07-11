package com.exadelinternship.carpool.entity;

import org.springframework.data.annotation.*;

public class Route {

    @Id
    private long id;
    private User user;
    private double[] startPoint;
    private double[] finishPoint;
    private double[][] viaPoints;
    private int routeUrl;
    private double[][] wayPoints;
    private double distance;
    private boolean isFavourite;


    public long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public double[] getStartPoint() {
        return startPoint;
    }

    public double[] getFinishPoint() {
        return finishPoint;
    }

    public double[][] getViaPoints() {
        return viaPoints;
    }

    public int getRouteUrl() {
        return routeUrl;
    }

    public double[][] getWayPoins() {
        return wayPoints;
    }

    public double getDistance() {
        return distance;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setStartPoint(double[] startPoint) {
        this.startPoint = startPoint;
    }

    public void setFinishPoint(double[] finishPoint) {
        this.finishPoint = finishPoint;
    }

    public void setWayPoints(double[][] wayPoints) {
        this.wayPoints = wayPoints;
    }

    public void setViaPoints(double[][] viaPoints) {
        this.viaPoints = viaPoints;
    }

    public void setRouteUrl(int routeUrl) {
        this.routeUrl = routeUrl;
    }

    public void setWayPoins(double[][] wayPoints) {
        this.wayPoints = wayPoints;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }
}
