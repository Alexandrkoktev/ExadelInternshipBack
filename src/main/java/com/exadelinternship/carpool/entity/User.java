package com.exadelinternship.carpool.entity;

import org.springframework.data.annotation.Id;

public class User {
    @Id
    private long id;
    private String name;
    private String phonenumber;
    private double rating;
    private String password;
    private String login;
    private int role;
    private String photourl;

    public User() {
    }

    public User(long id, String name, String phonenumber, double rating, String password, String login, int role, String photourl) {
        this.id = id;
        this.name = name;
        this.phonenumber = phonenumber;
        this.rating = rating;
        this.password = password;
        this.login = login;
        this.role = role;
        this.photourl = photourl;
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

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
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

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getPhotourl() {
        return photourl;
    }

    public void setPhotourl(String photourl) {
        this.photourl = photourl;
    }
}
