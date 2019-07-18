package com.exadelinternship.carpool.adapters;

import com.exadelinternship.carpool.dto.FavouriteRouteDTO;
import com.exadelinternship.carpool.entity.FavouriteRoute;
import com.exadelinternship.carpool.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavouriteRouteAdapter {
    @Autowired
    UserRepository userRepository;

     public FavouriteRoute favRouteDTOToFavRoute(FavouriteRouteDTO favRouteDTO){
         FavouriteRoute favR=new FavouriteRoute();
         favR.setId(favRouteDTO.getId());
         favR.setName(favRouteDTO.getName());
         favR.setUser(userRepository.getOne(favRouteDTO.getUserId()));
         favR.setRoute(favRouteDTO.getRoute());
         return  favR;
     }

     public FavouriteRouteDTO favRouteToFavRouteDTO(FavouriteRoute favRoute){
         FavouriteRouteDTO favRDTO=new FavouriteRouteDTO();
         favRDTO.setId(favRoute.getId());
         favRDTO.setName(favRoute.getName());
         favRDTO.setRoute(favRoute.getRoute());
         favRDTO.setUserId(favRoute.getUser().getId());
         return  favRDTO;
     }


}
