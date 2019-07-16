package com.exadelinternship.carpool.entity;

import javax.persistence.*;

@Entity
@Table(name="favouriteRoutes")
public class FavouriteRoute {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @Column(name="name",nullable=false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_fk")
    private User user;

    @OneToOne
    @JoinColumn(name = "route_fk")
    private Route route;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }



}
