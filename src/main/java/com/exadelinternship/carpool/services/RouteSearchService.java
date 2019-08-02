package com.exadelinternship.carpool.services;

import com.exadelinternship.carpool.adapters.ActiveRouteAdapter;
import com.exadelinternship.carpool.dto.ActiveRouteFastInformationDTO;
import com.exadelinternship.carpool.dto.BookingAddingDTO;
import com.exadelinternship.carpool.dto.RouteSearchDTO;
import com.exadelinternship.carpool.entity.ActiveRoute;
import com.exadelinternship.carpool.entity.Booking;
import com.exadelinternship.carpool.entity.impl.UserDetailsImpl;
import com.exadelinternship.carpool.repository.ActiveRouteRepository;
import com.exadelinternship.carpool.repository.BookingRepository;
import com.exadelinternship.carpool.services.helpers.RouteSearchHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class RouteSearchService {

    @Autowired
    ActiveRouteRepository activeRouteRepository;

    @Autowired
    ActiveRouteAdapter activeRouteAdapter;

    @Autowired
    BookingRepository bookingRepository;



    private boolean isTimeAvailableRoute(ActiveRoute route, List<ActiveRoute> activeRoutes){
        Timestamp startTime=route.getTimeAndDate();
        long duration=route.getRoute().getDuration();
        return !activeRoutes.stream().anyMatch(x->{
            return !((startTime.getTime()<x.getTimeAndDate().getTime()
                    && startTime.getTime()+duration<x.getTimeAndDate().getTime()) ||
                    (startTime.getTime()>x.getTimeAndDate().getTime()+x.getRoute().getDuration() &&
                            startTime.getTime()+duration > x.getTimeAndDate().getTime()+x.getRoute().getDuration()));
        });
    }

    private boolean isTimeAvailableBooking(ActiveRoute route, List<Booking> activeRoutes){
        Timestamp startTime=route.getTimeAndDate();
        long duration=route.getRoute().getDuration();
        return !activeRoutes.stream().anyMatch(x->{
            return !((startTime.getTime()<x.getActiveRoute().getTimeAndDate().getTime()
                    && startTime.getTime()+duration<x.getActiveRoute().getTimeAndDate().getTime()) ||
                    (startTime.getTime()>x.getActiveRoute().getTimeAndDate().getTime()+x.getActiveRoute().getRoute().getDuration() &&
                            startTime.getTime()+duration > x.getActiveRoute().getTimeAndDate().getTime()+x.getActiveRoute().getRoute().getDuration()));
        });
    }

    public List<ActiveRouteFastInformationDTO> getRoutes(RouteSearchDTO routeSearchDTO){
        long userId=((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        Set<ActiveRoute> routes=activeRouteRepository.getAllByEnabled(true);
        routes=routes.stream().filter(route->route.getUser().getId()!=userId&&route.getFreeSeats()>0).collect(Collectors.toSet());
        List<ActiveRoute> userRoutes=activeRouteRepository.getByUser_IdAndEnabled(userId,true).stream().collect(Collectors.toList());
        List<Booking> userBookings=bookingRepository.getByUser_IdAndActiveRoute_Enabled(userId,true).stream().collect(Collectors.toList());
        routes=routes.stream().filter(route->isTimeAvailableRoute(route,userRoutes)&&isTimeAvailableBooking(route,userBookings)).collect(Collectors.toSet());
        if(routeSearchDTO.getMeetPoint()!=null) {
           routes=routes.stream().filter(route -> RouteSearchHelper.isCloseEnough(routeSearchDTO.getMeetPoint(),
                   routeSearchDTO.getDestinationPoint(), route.getRoute().getWayPoints())).collect(Collectors.toSet());
         }
        if(routeSearchDTO.getDatetime()!=null) {
            routes=routes.stream().filter(route->route.getTimeAndDate().after(routeSearchDTO.getDatetime()))
                           .collect(Collectors.toSet());
        }
        List<ActiveRouteFastInformationDTO> result=new ArrayList<>();
        routes.forEach(route->result.add(activeRouteAdapter.activeRouteToActiveRouteFastInformationDTO(route)));
        return result.stream().sorted(Comparator.comparing(ActiveRouteFastInformationDTO::getTimeAndDate)).collect(Collectors.toList());
    }
}
