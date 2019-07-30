package com.exadelinternship.carpool.controllers;



import com.exadelinternship.carpool.dto.UserInformationDTO;
import com.exadelinternship.carpool.dto.UserListsDTO;
import com.exadelinternship.carpool.dto.UserProfileDTO;
import com.exadelinternship.carpool.dto.UserStatisticDTO;
import com.exadelinternship.carpool.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


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

    @GetMapping
    @RequestMapping("/statistic")
    public List<UserStatisticDTO> getStatistics(@RequestParam(name="order", required=false, defaultValue="true") boolean order,
                                                @RequestParam(name="mode", required=false, defaultValue="0") int mode,
                                                @RequestParam(name="page", required=false, defaultValue="0") int page,
                                                @RequestParam(name="name", required=false, defaultValue="") String name){
        return userService.getUsersStatistic(mode, order, page, name);
    }
}
