package com.exadelinternship.carpool.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="notifications")
public class Notification {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_fk")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activeRoute_fk")
    private ActiveRoute activeRoute;

    @Column(name="information",nullable=false)
    private String information;

    @Column(name="datetime",nullable=false)
    private Timestamp datetime;

    @Column(name="checked",nullable=false)
    private boolean checked;

    @Column(name = "driver")
    private boolean driver;

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
}
