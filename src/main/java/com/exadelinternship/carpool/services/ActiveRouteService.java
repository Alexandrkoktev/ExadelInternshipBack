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
import org.springframework.transaction.annotation.Transactional;

import javax.print.DocFlavor;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ActiveRouteService {

    private final String DELETE_MESSAGE = "Route from %s to %s in %s was deleted";
    private final String EDIT_MESSAGE = "Time of route from %s to %s was changed from %s to %s";
    private final String RATE_DRIVER = "You can rate your passengers on route from %s to %s";
    private final String RATE_PASSENGER = "You can rate your driver on route from %s to %s";

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

    public List<ActiveRouteFastInformationDTO> getPageOfActiveRoutesInformation(){
        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Set<ActiveRoute> activeRoutes = activeRouteRepository.getByUser_IdAndEnabled(user.getId(), true);
        return activeRoutesToActiveRoutesFastInformation(activeRoutes.stream().collect(Collectors.toList()));
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

    public RouteDTO getRoute(long id){
        ActiveRoute activeRoute = activeRouteRepository.getOne(id);
        return routeAdapter.routeToRouteDTO(activeRoute.getRoute());
    }

    public void setRating(RatingDTO rating){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Booking booking = bookingRepository.getOne(rating.getId());
        if(rating.getRate()>0 && rating.getRate()<6
                && booking!=null && !booking.getActiveRoute().isEnabled() && booking.getPassengerRating()==0
                && booking.getUser().getId()==userDetails.getId()){
            booking.setPassengerRating(rating.getRate());
            bookingRepository.save(booking);
            User passenger = booking.getActiveRoute().getUser();
            passenger.setRatingPassenger((passenger.getRatingPassenger() * passenger.getAmountOfVotersPassenger() + rating.getRate())
                    /passenger.getAmountOfVotersPassenger()+1);
            passenger.setAmountOfVotersPassenger(passenger.getAmountOfVotersPassenger()+1);
            userRepository.save(passenger);

        } else{

        }

    }

    public void addActiveRoute(ActiveRouteAddingDTO activeRouteAddingDTO){
        if(activeRouteAddingDTO.getStartPointName().length()<256 &&
                activeRouteAddingDTO.getFinishPointName().length()<256) {
            UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if(isTimeFree(activeRouteAddingDTO.getTimeAndDate(),activeRouteAddingDTO.getDuration()*1000,userDetails.getId())){
                activeRouteAddingDTO.setDuration(activeRouteAddingDTO.getDuration()*1000);
                activeRouteAddingDTO.setDistance(activeRouteAddingDTO.getDistance()/1000);
                Car car = carRepository.getOne(activeRouteAddingDTO.getCarId());
                User user = userRepository.getOne(userDetails.getId());
                Route route;
                if (activeRouteAddingDTO.isFavourite()) {
                    FavouriteRoute fRoute = favouriteRouteRepository.getOne(activeRouteAddingDTO.getFavouriteRouteId());
                    route = routeRepository.getOne(fRoute.getRoute().getId());
                } else {
                    route = routeAdapter.activeRouteAddingDTOToRoute(activeRouteAddingDTO, user);
                    route = routeRepository.save(route);
                }
                ActiveRoute activeRoute = activeRouteAdapter.activeRouteAddingDTOToActiveRoute(activeRouteAddingDTO, car, route, user);
                activeRouteRepository.save(activeRoute);
            } else{

            }
        }
    }

    private boolean isTimeFree(Timestamp startTime, long duration, long userId){
        return isTimeFreeForBookings(startTime,duration, bookingRepository.getByUser_IdAndActiveRoute_Enabled(userId,true)) &&
                isTimeFreeForRoutes(startTime,duration,activeRouteRepository.getByUser_IdAndEnabled(userId, true));
    }

    private boolean isTimeFreeForRoutes(Timestamp startTime, long duration, Set<ActiveRoute> activeRoutes){
        return !activeRoutes.stream().anyMatch(x->{
            return !((startTime.getTime()<x.getTimeAndDate().getTime()
                    && startTime.getTime()+duration<x.getTimeAndDate().getTime()) ||
                    (startTime.getTime()>x.getTimeAndDate().getTime()+x.getRoute().getDuration() &&
                            startTime.getTime()+duration > x.getTimeAndDate().getTime()+x.getRoute().getDuration()));
        });
    }

    private boolean isTimeFreeForBookings(Timestamp startTime, long duration, Set<Booking> activeRoutes){
        return !activeRoutes.stream().anyMatch(x->{
            return !((startTime.getTime()<x.getActiveRoute().getTimeAndDate().getTime()
                    && startTime.getTime()+duration<x.getActiveRoute().getTimeAndDate().getTime()) ||
                    (startTime.getTime()>x.getActiveRoute().getTimeAndDate().getTime()+x.getActiveRoute().getRoute().getDuration() &&
                            startTime.getTime()+duration > x.getActiveRoute().getTimeAndDate().getTime()+x.getActiveRoute().getRoute().getDuration()));
        });
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

    private List<ActiveRouteFastInformationDTO> activeRoutesToActiveRoutesFastInformation(List<ActiveRoute> activeRoutes){
        List<ActiveRouteFastInformationDTO> activeRouteFastInformationDTOS = new ArrayList<>();
        activeRoutes.stream()
                .forEach(x->activeRouteFastInformationDTOS.add(activeRouteAdapter.activeRouteToActiveRouteFastInformationDTO(x)));
        return activeRouteFastInformationDTOS;
    }

    private void deleteBookings(ActiveRoute activeRoute){
        activeRoute.getBookings().stream().forEach(x->deleteBooking(x));
        activeRoute.getBookings().clear();
    }

    private void deleteBooking(Booking booking){
        User user = booking.getUser();
        ActiveRoute activeRoute = booking.getActiveRoute();
        Notification notification = notificationAdapter.
                createNotification(user,
                        String.format(DELETE_MESSAGE,activeRoute.getRoute().getStartPointName(),
                                activeRoute.getRoute().getFinishPointName(),
                                toStringDate(activeRoute.getTimeAndDate().getTime())),activeRoute);
        notification.setDriver(false);
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
                        toStringDate(activeRoute.getTimeAndDate().getTime()),
                        toStringDate(newTime.getTime())),activeRoute);
        notification.setDriver(false);
        notificationRepository.save(notification);
    }

    private String toStringDate(long time){
        DateFormat d= new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return d.format(new Date(time));
    }

    public List<ActiveRouteFastInformationDTO> getHistory(){
        UserDetailsImpl user = (UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<ActiveRoute> activeRoutes = activeRouteRepository
                .getByUser_IdAndEnabled(user.getId(),false)
                .stream()
                .sorted((x,y)->{return x.getTimeAndDate().compareTo(y.getTimeAndDate());})
                .collect(Collectors.toList());
        return activeRoutesToActiveRoutesFastInformation(activeRoutes);
    }

    @Transactional
    @Scheduled(fixedRate = 600000)
    public void disablingRoutes(){
        Set<ActiveRoute> A = activeRouteRepository.getAllByEnabled(true);
                A.stream().forEach(x->{
            if(System.currentTimeMillis()>x.getTimeAndDate().getTime()+x.getRoute().getDuration()){
                x.setEnabled(false);
                x.getBookings().stream().forEach(y->{User user = y.getUser();
                    Notification notification = notificationAdapter.createNotification(user,String.format(RATE_PASSENGER, x.getRoute().getStartPointName(),x.getRoute().getFinishPointName()),x);
                    notification.setDriver(false);
                    notificationRepository.save(notification);
                    user.setAmountOfBookings(user.getAmountOfBookings()+1);
                    userRepository.save(user);
                });
                User user = x.getUser();
                Notification notification = notificationAdapter
                        .createNotification(user,String.format(RATE_DRIVER, x.getRoute().getStartPointName(),x.getRoute().getFinishPointName()),x);
                notification.setDriver(true);
                notificationRepository.save(notification);
                user.setAmountOfPassengers(user.getAmountOfPassengers()+x.getMaxSeats()-x.getFreeSeats());
                user.setDistance(user.getDistance()+x.getRoute().getDistance());
                userRepository.save(user);
                activeRouteRepository.save(x);
            }
        });
    }
}
