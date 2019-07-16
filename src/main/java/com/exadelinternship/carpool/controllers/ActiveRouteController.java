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

    @PostMapping
    @RequestMapping("/activeRoutes")
    public List<ActiveRouteFastInformationDTO> getActiveRoutesFastInformation(@Valid @RequestBody ActiveRouteIdentityDTO activeRouteIdentity){
        return activeRouteService.getPageOfActiveRoutesInformation(activeRouteIdentity);
    }

    @GetMapping
    @RequestMapping("/activeRoute/{id}")
    public ActiveRouteInformationDTO getActiveRoutes(@PathVariable long id){
        return activeRouteService.getActiveRouteInformation(id);
    }
}
