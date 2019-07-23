package com.exadelinternship.carpool.entity;


import com.exadelinternship.carpool.entity.enums.UserRole;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @Column(name="name",nullable=false)
    private String name;

    @Column(name="phoneNumber")
    private String phoneNumber;

    @Column(name="ratingDriver")
    private double ratingDriver;

    @Column(name="ratingPassenger")
    private double ratingPassenger;

    @Column(name="password")
    private String password;

    @Column(name="login")
    private String login;

    @Column(name="role")
    private UserRole role;

    @Column(name="photoURL")
    private String photoUrl;

    @Column(name="amountOfVotersDriver")
    private int amountOfVotersDriver;

    @Column(name="amountOfVotersPassenger")
    private int amountOfVotersPassenger;

    @OneToMany(fetch = FetchType.LAZY,mappedBy="user")
    private Set<Car> cars;

    @OneToMany(fetch = FetchType.LAZY,mappedBy="user")
    private Set<Route> routes;

    @OneToMany(fetch = FetchType.LAZY,mappedBy="user")
    private Set<Notification> notifications;

    @OneToMany(fetch = FetchType.LAZY,mappedBy="user")
    private Set<ActiveRoute> activeRoutes;


    @OneToMany(fetch = FetchType.LAZY,mappedBy="user")
    private Set<Booking> bookings;

    @OneToMany(fetch = FetchType.LAZY,mappedBy="user")
    private Set<FavouriteRoute> favouriteRoutes;

    public Set<FavouriteRoute> getFavouriteRoutes() {
        return favouriteRoutes;
    }

    public void setFavouriteRoutes(Set<FavouriteRoute> favouriteRoutes) {
        this.favouriteRoutes = favouriteRoutes;
    }

    public Set<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(Set<Notification> notifications) {
        this.notifications = notifications;
    }

    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }

    public Set<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(Set<Route> routes) {
        this.routes = routes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public double getRatingDriver() {
        return ratingDriver;
    }

    public void setRatingDriver(double ratingDriver) {
        this.ratingDriver = ratingDriver;
    }

    public double getRatingPassenger() {
        return ratingPassenger;
    }

    public void setRatingPassenger(double ratingPassenger) {
        this.ratingPassenger = ratingPassenger;
    }

    public int getAmountOfVotersDriver() {
        return amountOfVotersDriver;
    }

    public void setAmountOfVotersDriver(int amountOfVotersDriver) {
        this.amountOfVotersDriver = amountOfVotersDriver;
    }

    public int getAmountOfVotersPassenger() {
        return amountOfVotersPassenger;
    }

    public void setAmountOfVotersPassenger(int amountOfVotersPassenger) {
        this.amountOfVotersPassenger = amountOfVotersPassenger;
    }

    public Set<ActiveRoute> getActiveRoutes() {
        return activeRoutes;
    }

    public void setActiveRoutes(Set<ActiveRoute> activeRoutes) {
        this.activeRoutes = activeRoutes;
    }

    public Set<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
    }
}
