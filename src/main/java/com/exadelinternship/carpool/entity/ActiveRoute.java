package com.exadelinternship.carpool.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name="activeRoutes")
public class ActiveRoute {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="timeAndDate")
    private Timestamp timeAndDate;

    @Column(name="maxSeats")
    private short maxSeats;

    @Column(name="freeSeats")
    private short freeSeats;

    @Column(name="enabled")
    private boolean enabled;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "route_fk")
    private Route route;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_fk")
    private Car car;

    @OneToMany(fetch = FetchType.LAZY,mappedBy="activeRoute")
    private Set<Booking> bookings;

    @OneToMany(fetch = FetchType.LAZY,mappedBy="activeRoute")
    private Set<Notification> notifications;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_fk")
    private User user;


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

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Set<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public Set<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(Set<Notification> notifications) {
        this.notifications = notifications;
    }

}
