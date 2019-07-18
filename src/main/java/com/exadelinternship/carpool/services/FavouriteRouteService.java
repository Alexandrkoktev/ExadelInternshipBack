package com.exadelinternship.carpool.services;

import com.exadelinternship.carpool.adapters.FavouriteRouteAdapter;
import com.exadelinternship.carpool.dto.FavouriteRouteDTO;
import com.exadelinternship.carpool.entity.FavouriteRoute;
import com.exadelinternship.carpool.repository.FavouriteRouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FavouriteRouteService {
    @Autowired
    FavouriteRouteRepository favouriteRouteRepository;
    @Autowired
    FavouriteRouteAdapter favouriteRouteAdapter;

    public List<FavouriteRouteDTO>  getAllFavouriteRoutes(){
        List<FavouriteRouteDTO> result=new ArrayList<>();
        favouriteRouteRepository.findAll().forEach(favRoute -> result.add(favouriteRouteAdapter.favRouteToFavRouteDTO(favRoute)));
        return result;
    }

    public void saveFavouriteRoute(FavouriteRouteDTO favouriteRouteDTO){
        FavouriteRoute favouriteRoute=favouriteRouteAdapter.favRouteDTOToFavRoute(favouriteRouteDTO);
        favouriteRouteRepository.save(favouriteRoute);
    }

    public void deleteFavRouteById(long id){
        favouriteRouteRepository.deleteById(id);
    }
}
