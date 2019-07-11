package com.exadelinternship.carpool.entity;

import org.springframework.data.annotation.Id;

import java.sql.Date;

public class Notification {
    @Id
    private long id;
    private User user;
    private ActiveRoute activeRoute;
    private String information;
    private Date datetime;
    private boolean checked;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ActiveRoute getActiveRoute() {
        return activeRoute;
    }

    public void setActiveRoute(ActiveRoute activeRoute) {
        this.activeRoute = activeRoute;
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
