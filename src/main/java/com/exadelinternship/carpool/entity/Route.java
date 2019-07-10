package com.exadelinternship.carpool.entity;

import org.springframework.data.annotation.*;

public class Route {

    private long id;
    private UserEntity user;
    private double[] startPoint;
    private double[] finishPoint;
    private double[][] viaPoints;
    private int routeUrl;
    private double[][] wayPoins;
    double distance;
    boolean isFavourite;

    public Route(){
    }
    public Route(long id, UserEntity user, double[] startPoint, double[] finishPoint, double[][] viaPoints, int routeUrl, double[][] wayPoins, double distance, boolean isFavourite) {
        this.id = id;
        this.user = user;
        this.startPoint = startPoint;
        this.finishPoint = finishPoint;
        this.viaPoints = viaPoints;
        this.routeUrl = routeUrl;
        this.wayPoins = wayPoins;
        this.distance = distance;
        this.isFavourite = isFavourite;
    }

    public long getId() {
        return id;
    }

    public UserEntity getUser() {
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
        return wayPoins;
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

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public void setStartPoint(double[] startPoint) {
        this.startPoint = startPoint;
    }

    public void setFinishPoint(double[] finishPoint) {
        this.finishPoint = finishPoint;
    }

    public void setViaPoints(double[][] viaPoints) {
        this.viaPoints = viaPoints;
    }

    public void setRouteUrl(int routeUrl) {
        this.routeUrl = routeUrl;
    }

    public void setWayPoins(double[][] wayPoins) {
        this.wayPoins = wayPoins;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }
}
