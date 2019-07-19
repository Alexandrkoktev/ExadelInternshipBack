package com.exadelinternship.carpool.controllers;

import com.exadelinternship.carpool.dto.ActiveRouteFastInformationDTO;
import com.exadelinternship.carpool.dto.ActiveRouteIdentityDTO;
import com.exadelinternship.carpool.dto.ActiveRouteInformationDTO;
import com.exadelinternship.carpool.services.ActiveRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ActiveRouteController {

    @Autowired
    private ActiveRouteService activeRouteService;

    @GetMapping
    @RequestMapping("/activeRoutes/{pageNumber}")
    public List<ActiveRouteFastInformationDTO> getActiveRoutesFastInformation(@PathVariable int pageNumber){
        return activeRouteService.getPageOfActiveRoutesInformation(pageNumber);
    }

    @PostMapping
    @RequestMapping("/addRoute")
    public void addActiveRoute(@Valid @RequestBody ActiveRouteIdentityDTO activeRouteIdentity){

    }

    @DeleteMapping
    @RequestMapping("/deleteRoute")
    public void deleteActiveRoute(@Valid @RequestBody long id){
        deleteActiveRoute(id);
    }

    @GetMapping
    @RequestMapping("/activeRoute/{id}")
    public ActiveRouteInformationDTO getActiveRoutes(@PathVariable long id){
        return activeRouteService.getActiveRouteInformation(id);
    }
}
