package com.exadelinternship.carpool.controllers;



import com.exadelinternship.carpool.dto.UserInformationDTO;
import com.exadelinternship.carpool.dto.UserListsDTO;
import com.exadelinternship.carpool.dto.UserProfileDTO;
import com.exadelinternship.carpool.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    @RequestMapping("/header")
    public UserInformationDTO getUserInformation(){
        return userService.getUserInformation();
    }

    @GetMapping
    @RequestMapping("/home")
    public UserListsDTO getHomeLists(){
        return userService.getLists();
    }

    @GetMapping
    @RequestMapping("/profile")
    public UserProfileDTO getProfile(){
        return userService.getProfile();
    }
}
