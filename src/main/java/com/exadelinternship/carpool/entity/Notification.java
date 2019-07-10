package com.exadelinternship.carpool.entity;

import java.sql.Date;

public class Notification {
    private long id;
    private UserEntity user;
    private ActiveRoute activeroute;
    private String information;
    private Date datetime;
    private boolean checked;

    public Notification() {
    }

    public Notification(long id, UserEntity user, ActiveRoute activeroute, String information, Date datetime) {
        this.id = id;
        this.user = user;
        this.activeroute = activeroute;
        this.information = information;
        this.datetime = datetime;
        this.checked = false;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public ActiveRoute getActiveroute() {
        return activeroute;
    }

    public void setActiveroute(ActiveRoute activeroute) {
        this.activeroute = activeroute;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
