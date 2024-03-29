package com.exadelinternship.carpool.adapters;

import com.exadelinternship.carpool.dto.*;
import com.exadelinternship.carpool.entity.ActiveRoute;
import com.exadelinternship.carpool.entity.Booking;
import com.exadelinternship.carpool.entity.Notification;
import com.exadelinternship.carpool.entity.User;
import com.exadelinternship.carpool.entity.impl.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class UserAdapter {

    @Autowired
    ActiveRouteAdapter activeRouteAdapter;
    @Autowired
    BookingAdapter bookingAdapter;

    private final int AMOUNT_OF_NOTES = 3;

    public UserInformationDTO userToUserInformationDto(User user){
        UserInformationDTO userDTO = new UserInformationDTO();
        userDTO.setName(user.getName());
        userDTO.setRole(user.getRole());
        userDTO.setAmountOfNotifications(amountNotChecked(user.getNotifications()));
        return userDTO;
    }

    public UserListsDTO userToUserListDTO(Set<ActiveRoute> activeRoutes, Set<Booking> bookings){
        UserListsDTO userListsDTO = new UserListsDTO();
        userListsDTO.setActiveRoutes(getFirstActiveRoutes(activeRoutes, AMOUNT_OF_NOTES));
        userListsDTO.setBookings(getFirstBookings(bookings, AMOUNT_OF_NOTES));
        return userListsDTO;
    }

    private int amountNotChecked(Set<Notification> notifications){
        return (int)notifications.stream().filter(x->{return !x.isChecked();}).count();
    }

    private List<BookingFastInformationDTO> getFirstBookings(Set<Booking> bookings, int amount){
        List<BookingFastInformationDTO> result = new ArrayList<>();
        bookings.stream()
                .sorted((x,y)->{return x.getActiveRoute().getTimeAndDate().compareTo(y.getActiveRoute().getTimeAndDate());})
                .limit(amount).forEach(x->result.add(bookingAdapter.bookingToBookingFastInformationDTO(x)));
        return result;
    }

    private List<ActiveRouteFastInformationDTO> getFirstActiveRoutes(Set<ActiveRoute> activeRoutes, int amount){
        List<ActiveRouteFastInformationDTO> result = new ArrayList<>();
        activeRoutes.stream()
                .sorted((x,y)->{return x.getTimeAndDate().compareTo(y.getTimeAndDate());})
                .limit(amount).forEach(x->result.add(activeRouteAdapter.activeRouteToActiveRouteFastInformationDTO(x)));
        return result;
    }

    public UserProfileDTO userToUserProfileDTO(User user){
        UserProfileDTO userProfile = new UserProfileDTO();
        userProfile.setName(user.getName());
        userProfile.setPhoneNumber(user.getPhoneNumber());
        userProfile.setPhotoUrl(user.getPhotoUrl());
        userProfile.setRatingDriver(user.getRatingDriver());
        userProfile.setRatingPassenger(user.getRatingPassenger());
        return userProfile;
    }

    public UserDetailsImpl userToUserDetail(User user){
        UserDetailsImpl userDetails = new UserDetailsImpl();
        userDetails.setId(user.getId());
        userDetails.setPassword(user.getPassword());
        userDetails.setUsername(user.getLogin());
        userDetails.setRole(Collections.singleton(user.getRole()));
        return userDetails;
    }

    public UserStatisticDTO userToUserStatisticDTO(User user){
        UserStatisticDTO userStatistic = new UserStatisticDTO();
        userStatistic.setAmountOfBookings(user.getAmountOfBookings());
        userStatistic.setAmountOfPassengers(user.getAmountOfPassengers());
        userStatistic.setAmountOfRoutes(user.getAmountOfRoutes());
        userStatistic.setDistance(user.getDistance());
        userStatistic.setId(user.getId());
        userStatistic.setName(user.getName());
        userStatistic.setRatingDriver(user.getRatingDriver());
        userStatistic.setRatingPassenger(user.getRatingPassenger());
        return userStatistic;
    }
}
