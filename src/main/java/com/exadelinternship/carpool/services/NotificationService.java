package com.exadelinternship.carpool.services;

import com.exadelinternship.carpool.repository.NotificationRepository;
import com.exadelinternship.carpool.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    NotificationRepository notificationRepository;

    public boolean hasUnchecked(){
      return  notificationRepository.findAll().stream().filter(notif->!notif.isChecked()).count()>0;
    }

}
