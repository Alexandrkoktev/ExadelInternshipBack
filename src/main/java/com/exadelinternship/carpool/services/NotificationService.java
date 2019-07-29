package com.exadelinternship.carpool.services;

import com.exadelinternship.carpool.adapters.NotificationAdapter;
import com.exadelinternship.carpool.dto.NotificationDTO;
import com.exadelinternship.carpool.entity.Notification;
import com.exadelinternship.carpool.entity.impl.UserDetailsImpl;
import com.exadelinternship.carpool.repository.NotificationRepository;
import com.exadelinternship.carpool.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    NotificationRepository notificationRepository;
    @Autowired
    NotificationAdapter notificationAdapter;

    public List<NotificationDTO> getAllNotifications(){
        List<NotificationDTO> result=new ArrayList<>();
        long userId=((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        notificationRepository.findAllByUser_Id(userId).forEach(notif -> {
            result.add(notificationAdapter.notifToNotifDTO(notif));
            if(!notif.isChecked()){
                notif.setChecked(true);
                notificationRepository.save(notif);
            }
        });
        return result;
    }

    public void saveNotification (NotificationDTO notificationDTO){
        if(notificationDTO.getInformation().length()<=255) {
            long userId = ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
            Notification notification = notificationAdapter.notifDtoToNotif(notificationDTO, userId);
            notificationRepository.save(notification);
        }
    }


}
