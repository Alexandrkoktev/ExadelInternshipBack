package com.exadelinternship.carpool.controllers;

import com.exadelinternship.carpool.dto.FavouriteRouteDTO;
import com.exadelinternship.carpool.dto.FavouriteRouteInfoDTO;
import com.exadelinternship.carpool.dto.RouteDTO;
import com.exadelinternship.carpool.services.FavouriteRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class FavouriteRouteController {
    @Autowired
    FavouriteRouteService favouriteRouteService;

    @GetMapping("/profile/favouriteroutes")
    @ResponseBody
    public List<FavouriteRouteInfoDTO> getAllFavouriteRoutes(){
        return  favouriteRouteService.getAllFavouriteRoutes();
    }

    @GetMapping("/addRoute/favouriteroutes")
    @ResponseBody
    public List<FavouriteRouteInfoDTO> getAllFavouriteRoute(){
        return  favouriteRouteService.getAllFavouriteRoutes();
    }

    @PostMapping("/favouriteRoute")
    @ResponseBody
    public void addFavouriteRoute (@Valid @RequestBody FavouriteRouteDTO favouriteRouteDTO){
        favouriteRouteService.saveFavouriteRoute(favouriteRouteDTO);
    }

    @DeleteMapping("/favouriteRoute/{id}")
    @ResponseBody
    public void deleteFavouriteRoute(@PathVariable("id") long id){
        favouriteRouteService.deleteFavRouteById(id);
    }


    @GetMapping("/favouriteRoute/{id}")
    @ResponseBody
    public RouteDTO getFavouriteRoute(@PathVariable("id") long id){ return favouriteRouteService.getFavouriteRoute(id);}
}
