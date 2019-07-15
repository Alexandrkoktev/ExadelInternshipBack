package com.exadelinternship.carpool.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="routes")
public class Route {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @Column(name="startPoint",nullable=false)
    private String startPointName;

    @Column(name="finishPointName",nullable=false)
    private String finishPointName;

    @Column(name="startPointName",nullable=false)
    private double[] startPoint;

    @Column(name="finishPoint",nullable=false)
    private double[] finishPoint;

    @Column(name="viaPoints",nullable=false)
    private double[][] viaPoints;

    @Column(name="routeUrl",nullable=false)
    private int routeUrl;

    @Column(name="wayPoints",nullable=false)
    private double[][] wayPoints;

    @Column(name="distance",nullable=false)
    private double distance;

    @ManyToOne
    @JoinColumn(name = "user_fk")
    private User user;

    @OneToMany(mappedBy="route")
    private Set<ActiveRoute> activeRoutes;

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<ActiveRoute> getActiveRoutes() {
        return activeRoutes;
    }

    public void setActiveRoutes(Set<ActiveRoute> activeRoutes) {
        this.activeRoutes = activeRoutes;
    }


}
