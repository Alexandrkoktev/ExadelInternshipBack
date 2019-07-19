package com.exadelinternship.carpool.controllers;



import com.exadelinternship.carpool.dto.UserInformationDTO;
import com.exadelinternship.carpool.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    @RequestMapping("/main")
    public UserInformationDTO getUserInformation(){
        return userService.getUserInformation();
    }

}
