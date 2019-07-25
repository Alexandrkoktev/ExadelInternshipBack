package com.exadelinternship.carpool.dto;

import com.exadelinternship.carpool.entity.Booking;

import java.util.List;

public class UserListsDTO {
    List<BookingFastInformationDTO> bookings;
    List<ActiveRouteFastInformationDTO> activeRoutes;

    public List<BookingFastInformationDTO> getBookings() {
        return bookings;
    }

    public void setBookings(List<BookingFastInformationDTO> bookings) {
        this.bookings = bookings;
    }

    public List<ActiveRouteFastInformationDTO> getActiveRoutes() {
        return activeRoutes;
    }

    public void setActiveRoutes(List<ActiveRouteFastInformationDTO> activeRoutes) {
        this.activeRoutes = activeRoutes;
    }
}
