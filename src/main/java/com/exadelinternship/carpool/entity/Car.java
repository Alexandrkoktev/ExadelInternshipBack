package com.exadelinternship.carpool.entity;

public class Car {
    private long id;
    private UserEntity user;
    private String carinformation;
    private int maxseats;

    public Car(long id, UserEntity user, String carinformation, int maxseats) {
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

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
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
