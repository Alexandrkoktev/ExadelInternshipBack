package com.exadelinternship.carpool.dto;

import java.sql.Timestamp;

public class NotificationDTO {
    private long id;
    private long userId;
    private long activeRouteId;
    private String information;
    private Timestamp datetime;
    private boolean checked;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getActiveRouteId() {
        return activeRouteId;
    }

    public void setActiveRouteId(long activeRouteId) {
        this.activeRouteId = activeRouteId;
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
}
