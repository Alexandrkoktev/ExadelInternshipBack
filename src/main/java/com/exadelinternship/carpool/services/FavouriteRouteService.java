package com.exadelinternship.carpool.services;

import com.exadelinternship.carpool.adapters.FavouriteRouteAdapter;
import com.exadelinternship.carpool.dto.FavouriteRouteDTO;
import com.exadelinternship.carpool.dto.FavouriteRouteInfoDTO;
import com.exadelinternship.carpool.entity.FavouriteRoute;
import com.exadelinternship.carpool.entity.impl.UserDetailsImpl;
import com.exadelinternship.carpool.repository.FavouriteRouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FavouriteRouteService {
    @Autowired
    FavouriteRouteRepository favouriteRouteRepository;
    @Autowired
    FavouriteRouteAdapter favouriteRouteAdapter;

    public List<FavouriteRouteInfoDTO>  getAllFavouriteRoutes(){
        List<FavouriteRouteInfoDTO> result=new ArrayList<>();
        long userId=((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        favouriteRouteRepository.findAllByUser_Id(userId).forEach(favRoute -> result.add(favouriteRouteAdapter.favRouteToFavRouteInfoDTO(favRoute)));
        return result;
    }

    public void saveFavouriteRoute(FavouriteRouteDTO favouriteRouteDTO){
        if(favouriteRouteDTO.getName().length()<256) {
            long userId = ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
            FavouriteRoute favouriteRoute = favouriteRouteAdapter.favRouteDTOToFavRoute(favouriteRouteDTO, userId);
            favouriteRouteRepository.save(favouriteRoute);
        }
    }

    public void deleteFavRouteById(long id){
        favouriteRouteRepository.deleteById(id);
    }
}
