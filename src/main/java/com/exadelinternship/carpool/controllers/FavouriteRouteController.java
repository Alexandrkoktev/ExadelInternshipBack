package com.exadelinternship.carpool.controllers;

import com.exadelinternship.carpool.dto.FavouriteRouteDTO;
import com.exadelinternship.carpool.services.FavouriteRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class FavouriteRouteController {
    @Autowired
    FavouriteRouteService favouriteRouteService;

    @GetMapping("/profile/favouriteroutes")
    @ResponseBody
    public List<FavouriteRouteDTO> getAllFavouriteRoutes(){
        return  favouriteRouteService.getAllFavouriteRoutes();
    }

    public void addFavouriteRoute (@Valid @RequestBody FavouriteRouteDTO favouriteRouteDTO){
        favouriteRouteService.saveFavouriteRoute(favouriteRouteDTO);
    }

    public void deleteFavouriteRoute(long id){
        favouriteRouteService.deleteFavRouteById(id);
    }

}
