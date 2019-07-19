package com.exadelinternship.carpool.dto;

import com.exadelinternship.carpool.entity.ActiveRoute;
import com.exadelinternship.carpool.entity.Booking;
import com.exadelinternship.carpool.entity.enums.UserRole;

import java.util.List;

public class UserInformationDTO {
    private String name;
    private String photoURL;
    private UserRole role;
    private boolean AllNotificationsChecked;
    private List<ActiveRouteFastInformationDTO> activeRoutes;
    private List<BookingFastInformationDTO> bookings;

    public List<ActiveRouteFastInformationDTO> getActiveRoutes() {
        return activeRoutes;
    }

    public void setActiveRoutes(List<ActiveRouteFastInformationDTO> activeRoutes) {
        this.activeRoutes = activeRoutes;
    }

    public List<BookingFastInformationDTO> getBookings() {
        return bookings;
    }

    public void setBookings(List<BookingFastInformationDTO> bookings) {
        this.bookings = bookings;
    }

    public boolean isAllNotificationsChecked() {
        return AllNotificationsChecked;
    }

    public void setAllNotificationsChecked(boolean allNotificationsChecked) {
        AllNotificationsChecked = allNotificationsChecked;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }
}
