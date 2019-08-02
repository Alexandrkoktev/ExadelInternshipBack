package com.exadelinternship.carpool.controllers;

import com.exadelinternship.carpool.dto.*;
import com.exadelinternship.carpool.services.BookingService;
import com.exadelinternship.carpool.services.RouteSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BookingController {
    @Autowired
    BookingService bookingService;
    @Autowired
    RouteSearchService routeSearchService;

    @GetMapping
    @RequestMapping("/bookings")
    public List<BookingFastInformationDTO> getBookingFastInformation(){
        return bookingService.getPageOfBookingsInformation();
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
    @RequestMapping("/bookingValid")
    public boolean isValid(BookingValidationDTO booking){
        return routeSearchService.isValid(booking);
    }


    @GetMapping
    @RequestMapping("/bookings/history")
    public List<BookingFastInformationDTO> getHistory(){
        return bookingService.getHistory();
    }

    @PostMapping
    @RequestMapping("/createBooking")
    public void createBooking(@Valid @RequestBody BookingAddingDTO booking){
        bookingService.addBooking(booking);
    }

    @PostMapping
    @RequestMapping("/booking/setRating")
    public void setRating(@Valid @RequestBody RatingDTO rating){
        bookingService.setRating(rating);
    }
}
