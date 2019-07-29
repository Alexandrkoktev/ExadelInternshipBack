package com.exadelinternship.carpool.dto;

import java.sql.Timestamp;

public class NotificationDTO {

    private long routeOrBookingId;
    private String information;
    private Timestamp datetime;
    private boolean checked;
    private boolean driver;
    private long id;

    public long getRouteOrBookingId() {
        return routeOrBookingId;
    }

    public void setRouteOrBookingId(long routeOrBookingId) {
        this.routeOrBookingId = routeOrBookingId;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public Timestamp getDatetime() {
        return datetime;
    }

    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isDriver() {
        return driver;
    }

    public void setDriver(boolean driver) {
        this.driver = driver;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
