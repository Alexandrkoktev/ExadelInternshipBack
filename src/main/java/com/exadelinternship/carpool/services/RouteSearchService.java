package com.exadelinternship.carpool.services;

import com.exadelinternship.carpool.adapters.ActiveRouteAdapter;
import com.exadelinternship.carpool.dto.ActiveRouteFastInformationDTO;
import com.exadelinternship.carpool.dto.BookingAddingDTO;
import com.exadelinternship.carpool.dto.BookingValidationDTO;
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

    public List<ActiveRouteFastInformationDTO> getRoutes(RouteSearchDTO routeSearchDTO){
        long userId=((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        Set<ActiveRoute> routes=activeRouteRepository.getAllByEnabled(true);//ByRouteAfter(new Timestamp(System.currentTimeMillis()));
        routes.stream().filter(route->route.getUser().getId()!=userId).collect(Collectors.toList());
        List<ActiveRoute> userRoutes=activeRouteRepository.getByUser_IdAndEnabled(userId,true).stream().collect(Collectors.toList());
        List<Booking> userBookings=bookingRepository.getByUser_IdAndActiveRoute_Enabled(userId,true).stream().collect(Collectors.toList());
        routes.stream().filter(route->isTimeAvailableRoute(route,userRoutes)&&isTimeAvailableBooking(route,userBookings));
        if(routeSearchDTO.getMeetPoint()!=null) {
            routes.stream().filter(route -> RouteSearchHelper.isCloseEnough(routeSearchDTO.getMeetPoint(),
                   routeSearchDTO.getDestinationPoint(), route.getRoute().getWayPoints(),0.5)).collect(Collectors.toList());
        }
        if(routeSearchDTO.getDatetime()!=null) {
            routes.stream().filter(route->route.getTimeAndDate().after(routeSearchDTO.getDatetime()))
                           .collect(Collectors.toList());
        }
        List<ActiveRouteFastInformationDTO> result=new ArrayList<>();
        routes.forEach(route->result.add(activeRouteAdapter.activeRouteToActiveRouteFastInformationDTO(route)));
        return result;
    }

    public boolean isValid(BookingValidationDTO booking){
        return RouteSearchHelper.isCloseEnough(booking.getMeetPoint(),
                booking.getDestinationPoint(),
                activeRouteRepository.getOne(booking.getId()).getRoute().getWayPoints(),0.03);
    }
}
