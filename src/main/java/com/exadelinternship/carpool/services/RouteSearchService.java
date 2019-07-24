package com.exadelinternship.carpool.services;

import com.exadelinternship.carpool.adapters.ActiveRouteAdapter;
import com.exadelinternship.carpool.dto.ActiveRouteFastInformationDTO;
import com.exadelinternship.carpool.dto.BookingAddingDTO;
import com.exadelinternship.carpool.entity.ActiveRoute;
import com.exadelinternship.carpool.entity.impl.UserDetailsImpl;
import com.exadelinternship.carpool.repository.ActiveRouteRepository;
import com.exadelinternship.carpool.services.helpers.RouteSearchHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class RouteSearchService {

    @Autowired
    ActiveRouteRepository activeRouteRepository;

    @Autowired
    ActiveRouteAdapter activeRouteAdapter;

    public List<ActiveRouteFastInformationDTO> getRoutes(BookingAddingDTO bookingAddingDTO){
        long userId=((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        List<ActiveRoute> routes=new ArrayList<>();
        activeRouteRepository.getByRouteAfter(new Timestamp(System.currentTimeMillis()));
        if(bookingAddingDTO.getMeetPoint()!=null) {
            routes.stream().filter(route -> RouteSearchHelper.isCloseEnough(bookingAddingDTO.getMeetPoint(),
                    bookingAddingDTO.getDestinationPoint(), route.getRoute().getWayPoints()));
        }
        if(bookingAddingDTO.getDatetime()!=null) {
            routes.stream().filter(route->route.getTimeAndDate().after(bookingAddingDTO.getDatetime()));
        }
        List<ActiveRouteFastInformationDTO> result=new ArrayList<>();
        routes.forEach(route->result.add(activeRouteAdapter.activeRouteToActiveRouteFastInformationDTO(route)));
        return result;
    }
}
