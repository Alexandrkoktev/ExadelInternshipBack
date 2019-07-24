package com.exadelinternship.carpool.adapters;

import com.exadelinternship.carpool.dto.FavouriteRouteDTO;
import com.exadelinternship.carpool.entity.FavouriteRoute;
import com.exadelinternship.carpool.repository.RouteRepository;
import com.exadelinternship.carpool.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavouriteRouteAdapter {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RouteRepository routeRepository;

     public FavouriteRoute favRouteDTOToFavRoute(FavouriteRouteDTO favRouteDTO,long userId){
         FavouriteRoute favR=new FavouriteRoute();
         favR.setId(favRouteDTO.getId());
         favR.setName(favRouteDTO.getName());
         favR.setUser(userRepository.getOne(userId));
         favR.setRoute(routeRepository.getOne(favRouteDTO.getRouteId()));
         return  favR;
     }

     public FavouriteRouteDTO favRouteToFavRouteDTO(FavouriteRoute favRoute){
         FavouriteRouteDTO favRDTO=new FavouriteRouteDTO();
         favRDTO.setId(favRoute.getId());
         favRDTO.setName(favRoute.getName());
         favRDTO.setRouteId(favRoute.getRoute().getId());
         favRDTO.setStartPointName(favRoute.getRoute().getStartPointName());
         favRDTO.setEndPointName(favRoute.getRoute().getFinishPointName());
         return  favRDTO;
     }


}
