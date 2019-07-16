package com.exadelinternship.carpool.controllers;

import com.exadelinternship.carpool.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {
    @Autowired
    private NotificationService notificationService;
}
