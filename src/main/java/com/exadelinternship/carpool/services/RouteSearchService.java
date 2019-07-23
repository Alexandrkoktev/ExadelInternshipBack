package com.exadelinternship.carpool.services;

import com.exadelinternship.carpool.adapters.ActiveRouteAdapter;
import com.exadelinternship.carpool.dto.ActiveRouteFastInformationDTO;
import com.exadelinternship.carpool.entity.ActiveRoute;
import com.exadelinternship.carpool.entity.Route;
import com.exadelinternship.carpool.repository.ActiveRouteRepository;
import com.exadelinternship.carpool.services.helpers.RouteSearchHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class RouteSearchService {

    @Autowired
    ActiveRouteRepository activeRouteRepository;

    @Autowired
    ActiveRouteAdapter activeRouteAdapter;

    public List<ActiveRouteFastInformationDTO> getRoutes(Timestamp datetime,double[] startPoint,double [] endPoint){
        List<ActiveRoute> routes=new ArrayList<>();
        activeRouteRepository.getByRouteAfter(datetime);
        routes.stream().filter(route-> RouteSearchHelper.isCloseEnough(startPoint,endPoint,route.getRoute().getWayPoints()));
        List<ActiveRouteFastInformationDTO> result=new ArrayList<>();
        routes.forEach(route->result.add(activeRouteAdapter.activeRouteToActiveRouteFastInformationDTO(route)));
        return result;
    }
}
