package com.exadelinternship.carpool.adapters;

import com.exadelinternship.carpool.dto.BookingForDriverDTO;
import com.exadelinternship.carpool.entity.Booking;
import org.springframework.stereotype.Service;

@Service
public class BookingAdapter {
    public BookingForDriverDTO bookingToBookingFoDriverDTO(Booking booking){
        BookingForDriverDTO bookingForDriverDTO = new BookingForDriverDTO();
        bookingForDriverDTO.setDestinationPoint(booking.getDestinationPoint());
        bookingForDriverDTO.setMeetPoint(booking.getMeetPoint());
        bookingForDriverDTO.setName(booking.getUser().getName());
        bookingForDriverDTO.setPhoneNumber(booking.getUser().getPhoneNumber());
        return bookingForDriverDTO;
    }
}
