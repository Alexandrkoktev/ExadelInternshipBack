package com.exadelinternship.carpool.controllers;

import com.exadelinternship.carpool.dto.NotificationDTO;
import com.exadelinternship.carpool.dto.NotificationMessageDTO;
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

    @GetMapping("/notification/setChecked/{id}")
    @ResponseBody
    public void setChecked(@PathVariable long id){
        notificationService.setChecked(id);
    }

    @DeleteMapping("/notification")
    @ResponseBody
    public void deleteNotification(@Valid @RequestBody long id){
        notificationService.delete(id);
    }

    @PostMapping
    @RequestMapping("/booking/message")
    public void sendBookingMessage(@Valid @RequestBody NotificationMessageDTO message){
        notificationService.sendBookingMessages(message);
    }

    @PostMapping
    @RequestMapping("/activeRoute/message")
    public void sendRouteMessage(@Valid @RequestBody NotificationMessageDTO message){
        notificationService.sendRouteMessages(message);
    }
}
