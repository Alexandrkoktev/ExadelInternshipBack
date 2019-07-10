package com.exadelinternship.carpool.entity;

import org.springframework.data.annotation.Id;

public class Car {
    @Id
    private long id;
    private User user;
    private String carinformation;
    private int maxseats;

    public Car(long id, User user, String carinformation, int maxseats) {
        this.id = id;
        this.user = user;
        this.carinformation = carinformation;
        this.maxseats = maxseats;
    }

    public Car() {
    }

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

    public String getCarinformation() {
        return carinformation;
    }

    public void setCarinformation(String carinformation) {
        this.carinformation = carinformation;
    }

    public int getMaxseats() {
        return maxseats;
    }

    public void setMaxseats(int maxseats) {
        this.maxseats = maxseats;
    }
}
