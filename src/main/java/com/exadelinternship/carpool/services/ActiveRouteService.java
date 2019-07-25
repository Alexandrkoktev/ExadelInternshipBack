package com.exadelinternship.carpool.services;

import com.exadelinternship.carpool.adapters.ActiveRouteAdapter;
import com.exadelinternship.carpool.adapters.NotificationAdapter;
import com.exadelinternship.carpool.adapters.RouteAdapter;
import com.exadelinternship.carpool.dto.*;
import com.exadelinternship.carpool.entity.*;
import com.exadelinternship.carpool.entity.impl.UserDetailsImpl;
import com.exadelinternship.carpool.repository.*;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GeodeticMeasurement;
import org.gavaghan.geodesy.GlobalCoordinates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.print.DocFlavor;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ActiveRouteService {

    private final int PAGE_SIZE = 10;
    private final String DELETE_MESSAGE = "Route from %s to %s was deleted";
    private final String EDIT_MESSAGE = "Time of route from %s to %s was changed from %s to %s";

    @Autowired
    ActiveRouteRepository activeRouteRepository;
    @Autowired
    ActiveRouteAdapter activeRouteAdapter;
    @Autowired
    CarRepository carRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RouteRepository routeRepository;
    @Autowired
    FavouriteRouteRepository favouriteRouteRepository;
    @Autowired
    RouteAdapter routeAdapter;
    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    NotificationAdapter notificationAdapter;
    @Autowired
    NotificationRepository notificationRepository;

    public List<ActiveRouteFastInformationDTO> getPageOfActiveRoutesInformation(int pageNumber){
        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Set<ActiveRoute> activeRoutes = activeRouteRepository.getByUser_Id(user.getId());
        return activeRoutesToActiveRoutesFastInformation(getPageOfActiveRoutes(activeRoutes,pageNumber));
    }

    public void changeTime(ActiveRouteEditDTO activeRouteEdit){

        ActiveRoute activeRoute = activeRouteRepository.getOne(activeRouteEdit.getId());
        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(activeRoute!=null && activeRoute.getUser().getId() == user.getId()){
            notifyAllOfChangingTime(activeRoute,activeRouteEdit.getTimeAndDate());
            activeRoute.setTimeAndDate(activeRouteEdit.getTimeAndDate());
            activeRouteRepository.save(activeRoute);
        } else{

        }
    }

    public void addActiveRoute(ActiveRouteAddingDTO activeRouteAddingDTO){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Car car = carRepository.getOne(activeRouteAddingDTO.getCarId());
        User user = userRepository.getOne(userDetails.getId());
        Route route;
        if(activeRouteAddingDTO.isFavourite()){
            FavouriteRoute fRoute = favouriteRouteRepository.getOne(activeRouteAddingDTO.getFavouriteRouteId());
            route = routeRepository.getOne(fRoute.getRoute().getId());
        } else{
            route = routeAdapter.activeRouteAddingDTOToRoute(activeRouteAddingDTO, user);
            route = routeRepository.save(route);
        }
        ActiveRoute activeRoute = activeRouteAdapter.activeRouteAddingDTOToActiveRoute(activeRouteAddingDTO,car,route,user);
        activeRouteRepository.save(activeRoute);
    }

    public ActiveRouteInformationDTO getActiveRouteInformation(long id){
        UserDetailsImpl user = (UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ActiveRoute activeRoute = activeRouteRepository.getOne(id);
        if(activeRoute!=null && activeRoute.getUser().getId()==user.getId()) {
            return activeRouteAdapter.activeRouteToActiveRouteInformationDTO(activeRouteRepository.getOne(id));
        } else{
            return null;
        }
    }

    public void deleteActiveRoute(long id){
        UserDetailsImpl user = (UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ActiveRoute activeRoute = activeRouteRepository.getOne(id);
        if(activeRoute!=null && activeRoute.getUser().getId()==user.getId()){
            deleteBookings(activeRoute);
            activeRoute.setEnabled(false);
            activeRouteRepository.save(activeRoute);
        }
        else{

        }
    }

    private List<ActiveRoute> getPageOfActiveRoutes(Set<ActiveRoute> activeRoutes, int pageNumber){
        return activeRoutes.stream()
                .sorted((x,y)->{return x.getTimeAndDate().compareTo(y.getTimeAndDate());})
                .skip(pageNumber*PAGE_SIZE).limit(PAGE_SIZE).collect(Collectors.toList());
    }
    private List<ActiveRouteFastInformationDTO> activeRoutesToActiveRoutesFastInformation(List<ActiveRoute> activeRoutes){
        List<ActiveRouteFastInformationDTO> activeRouteFastInformationDTOS = new ArrayList<>();
        activeRoutes.stream()
                .forEach(x->activeRouteFastInformationDTOS.add(activeRouteAdapter.activeRouteToActiveRouteFastInformationDTO(x)));
        return activeRouteFastInformationDTOS;
    }

    private void deleteBookings(ActiveRoute activeRoute){
        activeRoute.getBookings().stream().forEach(x->deleteBooking(x));
    }

    private void deleteBooking(Booking booking){
        User user = booking.getUser();
        ActiveRoute activeRoute = booking.getActiveRoute();
        Notification notification = notificationAdapter.
                createNotification(user,
                        String.format(DELETE_MESSAGE,activeRoute.getRoute().getStartPointName(),
                                activeRoute.getRoute().getFinishPointName()),activeRoute);
        notificationRepository.save(notification);
        bookingRepository.delete(booking);
    }

    private void notifyAllOfChangingTime(ActiveRoute activeRoute, Timestamp newTime){
        activeRoute.getBookings().stream().forEach(x->notifyOfChangingTime(x, newTime));
    }
    private void notifyOfChangingTime(Booking booking, Timestamp newTime){
        User user = booking.getUser();
        ActiveRoute activeRoute = booking.getActiveRoute();
        Notification notification = notificationAdapter.
                createNotification(user,String.format(EDIT_MESSAGE,
                        activeRoute.getRoute().getStartPointName(),
                        activeRoute.getRoute().getFinishPointName(),
                        activeRoute.getTimeAndDate().toString(),newTime.toString()),activeRoute);
        notificationRepository.save(notification);
    }

    @Scheduled(fixedRate = 600000)
    public void disablingRoutes(){
        System.out.println(System.currentTimeMillis()+100000);
        Set<ActiveRoute> A = activeRouteRepository.getAllByEnabled(true);
                A.stream().forEach(x->{
            if(System.currentTimeMillis()>x.getTimeAndDate().getTime()+x.getRoute().getDuration()){
                x.setEnabled(false);
                activeRouteRepository.save(x);
            }
        });
    }
}
