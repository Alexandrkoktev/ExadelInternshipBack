package com.exadelinternship.carpool.controllers;

import com.exadelinternship.carpool.dto.*;
import com.exadelinternship.carpool.services.ActiveRouteService;
import com.exadelinternship.carpool.services.RouteSearchService;
import com.exadelinternship.carpool.services.helpers.RouteSearchHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ActiveRouteController {

    @Autowired
    private ActiveRouteService activeRouteService;

    @Autowired
    private RouteSearchService routeSearchService;

    @GetMapping
    @RequestMapping("/activeRoutes")
    public List<ActiveRouteFastInformationDTO> getActiveRoutesFastInformation(){
        return activeRouteService.getPageOfActiveRoutesInformation();
    }

    @GetMapping
    @RequestMapping("/activeRoutes/history")
    public List<ActiveRouteFastInformationDTO> getActiveRouteHistory(){
        return activeRouteService.getHistory();
    }

    @PostMapping
    @RequestMapping("/addRoute")
    public void addActiveRoute(@Valid @RequestBody ActiveRouteAddingDTO activeRoute){
        activeRouteService.addActiveRoute(activeRoute);
    }

    @DeleteMapping
    @RequestMapping("/deleteRoute")
    public void deleteActiveRoute(@Valid @RequestBody long id){
        activeRouteService.deleteActiveRoute(id);
    }

    @PostMapping
    @RequestMapping("/editRoute")
    public void changeTime(@Valid @RequestBody ActiveRouteEditDTO activeRoute){
        activeRouteService.changeTime(activeRoute);
    }

    @GetMapping
    @RequestMapping("/activeRoute/{id}")
    public ActiveRouteInformationDTO getActiveRoutes(@PathVariable long id){
        return activeRouteService.getActiveRouteInformation(id);
    }

    @PostMapping
    @RequestMapping("/searchRoutes")
    public List<ActiveRouteFastInformationDTO> searchRoutes(@Valid @RequestBody RouteSearchDTO routeSearchDTO){
        return  routeSearchService.getRoutes(routeSearchDTO);
    }

    @PostMapping
    @RequestMapping("/activeRoute/setRating")
    public void setRating(@Valid @RequestBody RatingDTO rating){
        activeRouteService.setRating(rating);
    }
}
