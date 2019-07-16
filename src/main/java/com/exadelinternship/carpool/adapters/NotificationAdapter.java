package com.exadelinternship.carpool.adapters;

import com.exadelinternship.carpool.dto.NotificationDTO;
import com.exadelinternship.carpool.entity.Notification;
import com.exadelinternship.carpool.repository.ActiveRouteRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class NotificationAdapter {
    @Autowired
    ActiveRouteRepository activeRouteRepository;
    public Notification notifDtoToNotif(NotificationDTO notificationDTO){
        Notification notif=new Notification();
        notif.setActiveRoute(activeRouteRepository.getOne(notificationDTO.getId()));
        notif.setDatetime(notificationDTO.getDatetime());
        notif.setInformation(notificationDTO.getInformation());
        return notif;
    }

}
