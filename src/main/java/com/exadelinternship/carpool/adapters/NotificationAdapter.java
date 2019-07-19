package com.exadelinternship.carpool.adapters;

import com.exadelinternship.carpool.dto.NotificationDTO;
import com.exadelinternship.carpool.entity.Notification;
import com.exadelinternship.carpool.repository.ActiveRouteRepository;
import com.exadelinternship.carpool.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationAdapter {
    @Autowired
    ActiveRouteRepository activeRouteRepository;
    @Autowired
    UserRepository userRepository;

    public Notification notifDtoToNotif(NotificationDTO notificationDTO,long userId){
        Notification notif=new Notification();
        notif.setActiveRoute(activeRouteRepository.getOne(notificationDTO.getId()));
        notif.setDatetime(notificationDTO.getDatetime());
        notif.setInformation(notificationDTO.getInformation());
        notif.setId(notificationDTO.getId());
        notif.setChecked(notificationDTO.isChecked());
        notif.setUser(userRepository.getOne(userId));
        return notif;
    }

    public NotificationDTO notifToNotifDTO(Notification notification){
        NotificationDTO notifDTO=new NotificationDTO();
        notifDTO.setChecked(notification.isChecked());
        notifDTO.setDatetime(notification.getDatetime());
        notifDTO.setId(notification.getId());
        notifDTO.setInformation(notification.getInformation());
        notifDTO.setActiveRouteId(notification.getActiveRoute().getId());
        return notifDTO;
    }

}
