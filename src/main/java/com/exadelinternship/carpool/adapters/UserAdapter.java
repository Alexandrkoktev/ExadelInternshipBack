package com.exadelinternship.carpool.adapters;

import com.exadelinternship.carpool.dto.UserInformationDTO;
import com.exadelinternship.carpool.entity.ActiveRoute;
import com.exadelinternship.carpool.entity.Booking;
import com.exadelinternship.carpool.entity.Notification;
import com.exadelinternship.carpool.entity.User;
import com.exadelinternship.carpool.entity.impl.UserDetailsImpl;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserAdapter {
    private final int AMOUNT_OF_NOTES = 5;
    public UserInformationDTO userToUserInformationDto(User user){
        UserInformationDTO userDTO = new UserInformationDTO();
        userDTO.setName(user.getName());
        userDTO.setPhotoURL(user.getPhotoUrl());
        userDTO.setRole(user.getRole());
        userDTO.setAllNotificationsChecked(isChecked(user.getNotifications()));
        userDTO.setActiveRoutes(getFirstActiveRoutes(user.getActiveRoutes(), AMOUNT_OF_NOTES));
        userDTO.setBookings(getFirstBookings(user.getBookings(), AMOUNT_OF_NOTES));
        return userDTO;
    }
    private boolean isChecked(Set<Notification> notifications){
        return notifications.stream().allMatch(x->{return x.isChecked();});
    }

    private List<Booking> getFirstBookings(Set<Booking> bookings, int amount){
        return bookings.stream()
                .sorted((x,y)->{return x.getActiveRoute().getTimeAndDate().compareTo(y.getActiveRoute().getTimeAndDate());})
                .limit(amount).collect(Collectors.toList());
    }
    private List<ActiveRoute> getFirstActiveRoutes(Set<ActiveRoute> activeRoutes, int amount){
        return activeRoutes.stream()
                .sorted((x,y)->{return x.getTimeAndDate().compareTo(y.getTimeAndDate());})
                .limit(amount).collect(Collectors.toList());
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
