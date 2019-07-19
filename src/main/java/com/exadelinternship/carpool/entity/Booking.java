package com.exadelinternship.carpool.entity;

import javax.persistence.*;

@Entity
@Table(name="bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="activeRoute_fk")
    private ActiveRoute activeRoute;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_fk")
    private User user;

    @Column(name="meetPoint")
    private double[] meetPoint;

    @Column(name="destinationPoint")
    private double[] destinationPoint;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ActiveRoute getActiveRoute() {
        return activeRoute;
    }

    public void setActiveRoute(ActiveRoute activeRoute) {
        this.activeRoute = activeRoute;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double[] getMeetPoint() {
        return meetPoint;
    }

    public void setMeetPoint(double[] meetPoint) {
        this.meetPoint = meetPoint;
    }

    public double[] getDestinationPoint() {
        return destinationPoint;
    }

    public void setDestinationPoint(double[] destinationPoint) {
        this.destinationPoint = destinationPoint;
    }
}
