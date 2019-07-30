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
import java.util.Optional;

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
        notificationRepository.findAllByUser_Id(userId).stream()
                .sorted((x,y)->{return x.getDatetime().compareTo(y.getDatetime());})
                .forEach(notif -> { result.add(notificationAdapter.notifToNotifDTO(notif, userId));
        });
        return result;
    }

    public void setChecked(long id){
        long userId=((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        Notification notifiction = notificationRepository.getOne(id);
        if(notifiction!=null && notifiction.getUser().getId() == userId){
            notifiction.setChecked(true);
            notificationRepository.save(notifiction);
        }
    }

    public void delete(long id){
        long userId=((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        Optional<Notification> notification = notificationRepository.findById(id);
        if(notification.isPresent() && notification.get().getUser().getId()==userId){
            notificationRepository.delete(notification.get());
        }
    }


}
