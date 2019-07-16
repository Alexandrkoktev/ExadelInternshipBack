package com.exadelinternship.carpool.controllers;


import com.exadelinternship.carpool.dto.UserAuthentificationDTO;
import com.exadelinternship.carpool.dto.UserInformationDTO;
import com.exadelinternship.carpool.entity.User;
import com.exadelinternship.carpool.repository.UserRepository;
import com.exadelinternship.carpool.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    @RequestMapping("/main")
    public UserInformationDTO getUserInformation(@Valid @RequestBody UserAuthentificationDTO user){
        return userService.getUserInformation(user);
    }

}
