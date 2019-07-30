package com.exadelinternship.carpool.adapters;

import com.exadelinternship.carpool.dto.BookingAddingDTO;
import com.exadelinternship.carpool.dto.BookingFastInformationDTO;
import com.exadelinternship.carpool.dto.BookingForDriverDTO;
import com.exadelinternship.carpool.dto.BookingInformationDTO;
import com.exadelinternship.carpool.entity.ActiveRoute;
import com.exadelinternship.carpool.entity.Booking;
import com.exadelinternship.carpool.entity.User;
import org.springframework.stereotype.Service;


@Service
public class BookingAdapter {

    public BookingForDriverDTO bookingToBookingFoDriverDTO(Booking booking){
        BookingForDriverDTO bookingForDriverDTO = new BookingForDriverDTO();
        bookingForDriverDTO.setDestinationPoint(booking.getDestinationPoint());
        bookingForDriverDTO.setMeetPoint(booking.getMeetPoint());
        bookingForDriverDTO.setName(booking.getUser().getName());
        bookingForDriverDTO.setPhoneNumber(booking.getUser().getPhoneNumber());
        bookingForDriverDTO.setRating(booking.getPassengerRating());
        return bookingForDriverDTO;
    }

    public BookingFastInformationDTO bookingToBookingFastInformationDTO(Booking booking){
        BookingFastInformationDTO bookingInformation = new BookingFastInformationDTO();
        bookingInformation.setFinishPointName(booking.getActiveRoute().getRoute().getFinishPointName());
        bookingInformation.setStartPointName(booking.getActiveRoute().getRoute().getStartPointName());
        bookingInformation.setId(booking.getId());
        bookingInformation.setTimeAndDate(booking.getActiveRoute().getTimeAndDate());
        return bookingInformation;
    }

    public BookingInformationDTO bookingToBookingInformationDTO(Booking booking){
        BookingInformationDTO bookingInformationDTO = new BookingInformationDTO();
        bookingInformationDTO.setCarInformation(booking.getActiveRoute().getCar().getCarInformation());
        bookingInformationDTO.setId(booking.getId());
        bookingInformationDTO.setTimeAndDate(booking.getActiveRoute().getTimeAndDate());
        bookingInformationDTO.setFreeSeats(booking.getActiveRoute().getFreeSeats());
        bookingInformationDTO.setMaxSeats(booking.getActiveRoute().getMaxSeats());
        bookingInformationDTO.setFinishPointName(booking.getActiveRoute().getRoute().getFinishPointName());
        bookingInformationDTO.setStartPoint(booking.getActiveRoute().getRoute().getStartPoint());
        bookingInformationDTO.setStartPointName(booking.getActiveRoute().getRoute().getStartPointName());
        bookingInformationDTO.setFinishPoint(booking.getActiveRoute().getRoute().getFinishPoint());
        bookingInformationDTO.setRouteUrl(booking.getActiveRoute().getRoute().getRouteUrl());
        bookingInformationDTO.setDestinationPoint(booking.getDestinationPoint());
        bookingInformationDTO.setMeetPoint(booking.getMeetPoint());
        bookingInformationDTO.setRating(booking.getDriverRating());
        bookingInformationDTO.setEnabled(booking.getActiveRoute().isEnabled());
        return bookingInformationDTO;
    }

    public Booking bookingAddingToBooking(BookingAddingDTO bookingAdding, User user, ActiveRoute activeRoute){
        Booking booking = new Booking();
        booking.setActiveRoute(activeRoute);
        booking.setDestinationPoint(bookingAdding.getDestinationPoint());
        booking.setUser(user);
        booking.setMeetPoint(bookingAdding.getMeetPoint());
        booking.setPassengerRating(0);
        booking.setDriverRating(0);
        return booking;
    }
}
