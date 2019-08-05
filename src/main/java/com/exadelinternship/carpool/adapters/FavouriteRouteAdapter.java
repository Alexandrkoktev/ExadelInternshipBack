package com.exadelinternship.carpool.adapters;

import com.exadelinternship.carpool.dto.FavouriteRouteDTO;
import com.exadelinternship.carpool.dto.FavouriteRouteInfoDTO;
import com.exadelinternship.carpool.dto.RouteDTO;
import com.exadelinternship.carpool.entity.FavouriteRoute;
import com.exadelinternship.carpool.repository.ActiveRouteRepository;
import com.exadelinternship.carpool.repository.RouteRepository;
import com.exadelinternship.carpool.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavouriteRouteAdapter {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ActiveRouteRepository activeRouteRepository;

     public FavouriteRoute favRouteDTOToFavRoute(FavouriteRouteDTO favRouteDTO,long userId){
         FavouriteRoute favR=new FavouriteRoute();
         favR.setName(favRouteDTO.getName());
         favR.setUser(userRepository.getOne(userId));
         favR.setRoute(activeRouteRepository.getOne(favRouteDTO.getRouteId()).getRoute());
         return  favR;
     }

     public FavouriteRouteInfoDTO favRouteToFavRouteInfoDTO(FavouriteRoute favRoute){
         FavouriteRouteInfoDTO favRDTO=new FavouriteRouteInfoDTO();
         favRDTO.setName(favRoute.getName());
         favRDTO.setRouteId(favRoute.getRoute().getId());
         favRDTO.setStartPointName(favRoute.getRoute().getStartPointName());
         favRDTO.setEndPointName(favRoute.getRoute().getFinishPointName());
         favRDTO.setId(favRoute.getId());
         return  favRDTO;
     }

     public RouteDTO favRouteToRouteDTO(FavouriteRoute favRoute){
         RouteDTO routeDTO=new RouteDTO();
         routeDTO.setFinishPoint(favRoute.getRoute().getFinishPoint());
         routeDTO.setStartPoint(favRoute.getRoute().getStartPoint());
         routeDTO.setViaPoints(favRoute.getRoute().getViaPoints());
         return routeDTO;
     }


}
