package com.exadelinternship.carpool.adapters;

import com.exadelinternship.carpool.dto.NotificationDTO;
import com.exadelinternship.carpool.entity.ActiveRoute;
import com.exadelinternship.carpool.entity.Booking;
import com.exadelinternship.carpool.entity.Notification;
import com.exadelinternship.carpool.entity.User;
import com.exadelinternship.carpool.repository.ActiveRouteRepository;
import com.exadelinternship.carpool.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class NotificationAdapter {
    @Autowired
    ActiveRouteRepository activeRouteRepository;
    @Autowired
    UserRepository userRepository;

    public NotificationDTO notifToNotifDTO(Notification notification, long id){
        NotificationDTO notifDTO=new NotificationDTO();
        notifDTO.setChecked(notification.isChecked());
        notifDTO.setDatetime(notification.getDatetime());
        notifDTO.setInformation(notification.getInformation());
        notifDTO.setDriver(notification.isDriver());
        if(notification.isDriver()){
            notifDTO.setRouteOrBookingId(notification.getActiveRoute().getId());
        }
        else{
            Optional<Booking> booking = notification.getActiveRoute().getBookings().stream()
                    .filter(x->x.getUser().getId()==id).findFirst();
            if(booking.isPresent()){
                notifDTO.setRouteOrBookingId(booking.get().getId());
            }

        }
        return notifDTO;
    }

    public Notification createNotification(User user, String information, ActiveRoute activeRoute){
        Notification notification = new Notification();
        notification.setActiveRoute(activeRoute);
        notification.setChecked(false);
        notification.setDatetime(new Timestamp(System.currentTimeMillis()));
        notification.setInformation(information);
        notification.setUser(user);
        return notification;
    }

}
