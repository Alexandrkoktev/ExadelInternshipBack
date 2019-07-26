package com.exadelinternship.carpool.adapters;

import com.exadelinternship.carpool.dto.ActiveRouteAddingDTO;
import com.exadelinternship.carpool.dto.ActiveRouteFastInformationDTO;
import com.exadelinternship.carpool.dto.ActiveRouteInformationDTO;
import com.exadelinternship.carpool.dto.BookingForDriverDTO;
import com.exadelinternship.carpool.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ActiveRouteAdapter {
    @Autowired
    BookingAdapter bookingAdapter;

    public ActiveRouteFastInformationDTO activeRouteToActiveRouteFastInformationDTO(ActiveRoute activeRoute){
        ActiveRouteFastInformationDTO activeRouteFastInformationDTO = new ActiveRouteFastInformationDTO();
        activeRouteFastInformationDTO.setFinishPointName(activeRoute.getRoute().getFinishPointName());
        activeRouteFastInformationDTO.setStartPointName(activeRoute.getRoute().getStartPointName());
        activeRouteFastInformationDTO.setId(activeRoute.getId());
        activeRouteFastInformationDTO.setTimeAndDate(activeRoute.getTimeAndDate());
        return activeRouteFastInformationDTO;
    }

    public ActiveRoute activeRouteAddingDTOToActiveRoute(ActiveRouteAddingDTO activeRouteAddingDTO, Car car, Route route, User user){
        ActiveRoute activeRoute = new ActiveRoute();
        activeRoute.setFreeSeats(activeRouteAddingDTO.getMaxSeats());
        activeRoute.setCar(car);
        activeRoute.setRoute(route);
        activeRoute.setMaxSeats(activeRouteAddingDTO.getMaxSeats());
        activeRoute.setTimeAndDate(activeRouteAddingDTO.getTimeAndDate());
        activeRoute.setUser(user);
        activeRoute.setEnabled(true);
        return activeRoute;
    }

    public ActiveRouteInformationDTO activeRouteToActiveRouteInformationDTO(ActiveRoute activeRoute){
        ActiveRouteInformationDTO activeRouteInformationDTO = new ActiveRouteInformationDTO();
        activeRouteInformationDTO.setCarInformation(activeRoute.getCar().getCarInformation());
        activeRouteInformationDTO.setId(activeRoute.getId());
        activeRouteInformationDTO.setTimeAndDate(activeRoute.getTimeAndDate());
        activeRouteInformationDTO.setFreeSeats(activeRoute.getFreeSeats());
        activeRouteInformationDTO.setMaxSeats(activeRoute.getMaxSeats());
        activeRouteInformationDTO.setFinishPointName(activeRoute.getRoute().getFinishPointName());
        activeRouteInformationDTO.setStartPoint(activeRoute.getRoute().getStartPoint());
        activeRouteInformationDTO.setStartPointName(activeRoute.getRoute().getStartPointName());
        activeRouteInformationDTO.setFinishPoint(activeRoute.getRoute().getFinishPoint());
        activeRouteInformationDTO.setRouteUrl(activeRoute.getRoute().getRouteUrl());
        activeRouteInformationDTO.setBookings(bookingsToBookingsForDrivers(activeRoute.getBookings()));
        return activeRouteInformationDTO;
    }

    private List<BookingForDriverDTO> bookingsToBookingsForDrivers(Set<Booking> bookings){
        List<BookingForDriverDTO> bookingForDriverDTOS = new ArrayList<>();
        bookings.stream().forEach(x->bookingForDriverDTOS.add(bookingAdapter.bookingToBookingFoDriverDTO(x)));
        return bookingForDriverDTOS;
    }

}
