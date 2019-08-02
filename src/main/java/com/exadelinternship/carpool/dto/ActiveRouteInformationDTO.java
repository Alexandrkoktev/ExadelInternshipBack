package com.exadelinternship.carpool.dto;

import java.sql.Timestamp;
import java.util.List;

public class ActiveRouteInformationDTO {
    private long id;
    private Timestamp timeAndDate;
    private short maxSeats;
    private short freeSeats;
    private String carInformation;
    private String startPointName;
    private String finishPointName;
    private double[] startPoint;
    private double[] finishPoint;
    private double[][] viaPoints;
    private boolean enabled;
    private List<BookingForDriverDTO> bookings;
    private long routeId;

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

    public long getRouteId() {
        return routeId;
    }

    public void setRouteId(long routeId) {
        this.routeId = routeId;
    }

    public String getCarInformation() {
        return carInformation;
    }

    public void setCarInformation(String carInformation) {
        this.carInformation = carInformation;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public short getFreeSeats() {
        return freeSeats;
    }

    public void setFreeSeats(short freeSeats) {
        this.freeSeats = freeSeats;
    }

    public List<BookingForDriverDTO> getBookings() {
        return bookings;
    }

    public void setBookings(List<BookingForDriverDTO> bookings) {
        this.bookings = bookings;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
