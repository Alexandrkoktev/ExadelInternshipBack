package com.exadelinternship.carpool.adapters;

import com.exadelinternship.carpool.dto.ActiveRouteAddingDTO;
import com.exadelinternship.carpool.dto.RouteDTO;
import com.exadelinternship.carpool.entity.Route;
import com.exadelinternship.carpool.entity.User;
import org.springframework.stereotype.Service;

@Service
public class RouteAdapter {
    public Route activeRouteAddingDTOToRoute(ActiveRouteAddingDTO activeRouteAddingDTO, User user){
        Route route = new Route();
        route.setDistance(activeRouteAddingDTO.getDistance());
        route.setFinishPoint(activeRouteAddingDTO.getFinishPoint());
        route.setFinishPointName(activeRouteAddingDTO.getFinishPointName());
        route.setRouteUrl(activeRouteAddingDTO.getRouteUrl());
        route.setStartPoint(activeRouteAddingDTO.getStartPoint());
        route.setStartPointName(activeRouteAddingDTO.getStartPointName());
        route.setUser(user);
        route.setViaPoints(activeRouteAddingDTO.getViaPoints());
        route.setWayPoints(activeRouteAddingDTO.getWayPoints());
        route.setDuration(activeRouteAddingDTO.getDuration());
        return route;
    }
    public RouteDTO routeToRouteDTO(Route route) {
        RouteDTO routeDTO = new RouteDTO();
        routeDTO.setFinishPoint(route.getFinishPoint());
        routeDTO.setStartPoint(route.getStartPoint());
        routeDTO.setViaPoints(route.getViaPoints());
        return routeDTO;
    }
}
