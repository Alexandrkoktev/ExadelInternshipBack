package com.exadelinternship.carpool.controllers;

import com.exadelinternship.carpool.dto.*;
import com.exadelinternship.carpool.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BookingController {
    @Autowired
    BookingService bookingService;

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
    public void deleteBooking(@Valid @RequestBody long id)throws Exception{
        bookingService.deleteBooking(id);
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
    public void setRating(@Valid @RequestBody RatingDTO rating)throws Exception{
        bookingService.setRating(rating);
    }
}
