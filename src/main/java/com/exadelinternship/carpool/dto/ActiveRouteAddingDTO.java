package com.exadelinternship.carpool.dto;

import com.exadelinternship.carpool.entity.Car;

import java.sql.Timestamp;

public class ActiveRouteAddingDTO {
    private Timestamp timeAndDate;
    private long duration;
    private short maxSeats;
    private long carId;
    private boolean favourite;
    private long favouriteRouteId;
    private String startPointName;
    private String finishPointName;
    private double[] startPoint;
    private double[] finishPoint;
    private double[][] viaPoints;
    private int routeUrl;
    private double[][] wayPoints;
    private double distance;

    public boolean isFavourite() {
        return favourite;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }

    public long getFavouriteRouteId() {
        return favouriteRouteId;
    }

    public void setFavouriteRouteId(long favouriteRouteId) {
        this.favouriteRouteId = favouriteRouteId;
    }

    public Timestamp getTimeAndDate() {
        return timeAndDate;
    }

    public void setTimeAndDate(Timestamp timeAndDate) {
        this.timeAndDate = timeAndDate;
    }

    public short getMaxSeats() {
        return maxSeats;
    }

    public void setMaxSeats(short maxSeats) {
        this.maxSeats = maxSeats;
    }

    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }

    public String getStartPointName() {
        return startPointName;
    }

    public void setStartPointName(String startPointName) {
        this.startPointName = startPointName;
    }

    public String getFinishPointName() {
        return finishPointName;
    }

    public void setFinishPointName(String finishPointName) {
        this.finishPointName = finishPointName;
    }

    public double[] getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(double[] startPoint) {
        this.startPoint = startPoint;
    }

    public double[] getFinishPoint() {
        return finishPoint;
    }

    public void setFinishPoint(double[] finishPoint) {
        this.finishPoint = finishPoint;
    }

    public double[][] getViaPoints() {
        return viaPoints;
    }

    public void setViaPoints(double[][] viaPoints) {
        this.viaPoints = viaPoints;
    }

    public int getRouteUrl() {
        return routeUrl;
    }

    public void setRouteUrl(int routeUrl) {
        this.routeUrl = routeUrl;
    }

    public double[][] getWayPoints() {
        return wayPoints;
    }

    public void setWayPoints(double[][] wayPoints) {
        this.wayPoints = wayPoints;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }
}
