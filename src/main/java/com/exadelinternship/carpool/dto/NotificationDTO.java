package com.exadelinternship.carpool.dto;

import java.sql.Timestamp;

public class NotificationDTO {
    private long activeRouteId;
    private String information;
    private Timestamp datetime;
    private boolean checked;

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
