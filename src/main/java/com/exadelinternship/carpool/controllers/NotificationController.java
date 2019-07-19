package com.exadelinternship.carpool.controllers;

import com.exadelinternship.carpool.dto.NotificationDTO;
import com.exadelinternship.carpool.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @GetMapping("/notifications")
    @ResponseBody
    public List<NotificationDTO> getAllNotifications(){
        return  notificationService.getAllNotifications();
    }

    public void addNotification(@Valid @RequestBody NotificationDTO notificationDTO){
        notificationService.saveNotification(notificationDTO);
    }
}
