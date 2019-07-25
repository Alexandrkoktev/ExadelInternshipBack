package com.exadelinternship.carpool.services;

import com.exadelinternship.carpool.adapters.ActiveRouteAdapter;
import com.exadelinternship.carpool.dto.ActiveRouteFastInformationDTO;
import com.exadelinternship.carpool.dto.BookingAddingDTO;
import com.exadelinternship.carpool.entity.ActiveRoute;
import com.exadelinternship.carpool.entity.Booking;
import com.exadelinternship.carpool.entity.impl.UserDetailsImpl;
import com.exadelinternship.carpool.repository.ActiveRouteRepository;
import com.exadelinternship.carpool.repository.BookingRepository;
import com.exadelinternship.carpool.services.helpers.RouteSearchHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RouteSearchService {

    @Autowired
    ActiveRouteRepository activeRouteRepository;

    @Autowired
    ActiveRouteAdapter activeRouteAdapter;

    @Autowired
    BookingRepository bookingRepository;

    private boolean isTimeAvailableRoute(ActiveRoute route,List<ActiveRoute> userRoutes){
        for (ActiveRoute userRoute:userRoutes){
            if (route.getTimeAndDate().getTime()+route.getRoute().getDuration()>userRoute.getTimeAndDate().getTime()||
            route.getTimeAndDate().getTime()<userRoute.getTimeAndDate().getTime()+userRoute.getRoute().getDuration()){
                return false;
            }
        }
        return true;
    }
    private boolean isTimeAvailableBooking(ActiveRoute route,List<Booking> userBookings){
        for (Booking userBooking:userBookings){
            if (route.getTimeAndDate().getTime()+route.getRoute().getDuration()>userBooking.getActiveRoute().getTimeAndDate().getTime()||
                    route.getTimeAndDate().getTime()<userBooking.getActiveRoute().getTimeAndDate().getTime()+userBooking.getActiveRoute().getRoute().getDuration()){
                return false;
            }
        }
        return true;
    }

    public List<ActiveRouteFastInformationDTO> getRoutes(BookingAddingDTO bookingAddingDTO){
        long userId=((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        List<ActiveRoute> routes=activeRouteRepository.getByRouteAfter(new Timestamp(System.currentTimeMillis()));
        routes.stream().filter(route->route.getUser().getId()!=userId&&route.isEnabled()).collect(Collectors.toList());
        List<ActiveRoute> userRoutes=activeRouteRepository.getByUser_Id(userId).stream().collect(Collectors.toList());
        List<Booking> userBookings=bookingRepository.getByUser_Id(userId).stream().collect(Collectors.toList());
        routes.stream().filter(route->isTimeAvailableRoute(route,userRoutes)&&isTimeAvailableBooking(route,userBookings));
        if(bookingAddingDTO.getMeetPoint()!=null) {
            routes.stream().filter(route -> RouteSearchHelper.isCloseEnough(bookingAddingDTO.getMeetPoint(),
                    bookingAddingDTO.getDestinationPoint(), route.getRoute().getWayPoints())).collect(Collectors.toList());
        }
        if(bookingAddingDTO.getDatetime()!=null) {
            routes.stream().filter(route->route.getTimeAndDate().after(bookingAddingDTO.getDatetime()))
                           .collect(Collectors.toList());
        }
        List<ActiveRouteFastInformationDTO> result=new ArrayList<>();
        routes.forEach(route->result.add(activeRouteAdapter.activeRouteToActiveRouteFastInformationDTO(route)));
        return result;
    }
}
