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

    @GetMapping
    @RequestMapping("/bookings/{pageNumber}")
    public List<BookingFastInformationDTO> getBookingFastInformation(@PathVariable int pageNumber){
        return bookingService.getPageOfBookingsInformation(pageNumber);
    }

    @GetMapping
    @RequestMapping("/booking/{id}")
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
