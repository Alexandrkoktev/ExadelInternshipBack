package com.exadelinternship.carpool.controllers;

import com.exadelinternship.carpool.dto.NotificationDTO;
import com.exadelinternship.carpool.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
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
