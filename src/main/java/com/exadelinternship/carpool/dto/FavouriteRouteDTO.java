package com.exadelinternship.carpool.dto;

import com.exadelinternship.carpool.entity.Route;

public class FavouriteRouteDTO {
    private long id;
    private String name;
    private long userId;
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

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }
}
