package com.exadelinternship.carpool.controllers;

import com.exadelinternship.carpool.dto.ActiveRouteIdentityDTO;
import com.exadelinternship.carpool.dto.BookingAddingDTO;
import com.exadelinternship.carpool.dto.BookingFastInformationDTO;
import com.exadelinternship.carpool.dto.BookingInformationDTO;
import com.exadelinternship.carpool.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class BookingController {
    @Autowired
    BookingService bookingService;
    @PostMapping
    @RequestMapping("/bookings")
    public List<BookingFastInformationDTO> getBookingFastInformation(@Valid @RequestBody ActiveRouteIdentityDTO activeRouteIdentity){
        return bookingService.getPageOfBookingsInformation(activeRouteIdentity);
    }

    @GetMapping
    @RequestMapping("/bookings/{id}")
    public BookingInformationDTO getActiveRoutes(@PathVariable long id){
        return bookingService.getBookingInformation(id);
    }

    @DeleteMapping
    @RequestMapping("/booking")
    public void deleteBooking(@Valid @RequestBody long id){
        bookingService.deleteBooking(id);
    }


    @PostMapping
    @RequestMapping("/createBooking")
    public void createBooking(@Valid @RequestBody BookingAddingDTO booking){
        bookingService.addBooking(booking);
    }
}
