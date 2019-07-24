package com.exadelinternship.carpool.adapters;

import com.exadelinternship.carpool.dto.ActiveRouteFastInformationDTO;
import com.exadelinternship.carpool.dto.BookingFastInformationDTO;
import com.exadelinternship.carpool.dto.UserInformationDTO;
import com.exadelinternship.carpool.dto.UserProfileDTO;
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
import java.util.stream.Collectors;

@Service
public class UserAdapter {

    @Autowired
    ActiveRouteAdapter activeRouteAdapter;
    @Autowired
    BookingAdapter bookingAdapter;

    private final int AMOUNT_OF_NOTES = 5;

    public UserInformationDTO userToUserInformationDto(User user){
        UserInformationDTO userDTO = new UserInformationDTO();
        userDTO.setName(user.getName());
        userDTO.setRole(user.getRole());
        userDTO.setAllNotificationsChecked(isChecked(user.getNotifications()));
        return userDTO;
    }

    private boolean isChecked(Set<Notification> notifications){
        return notifications.stream().allMatch(x->{return x.isChecked();});
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
}
