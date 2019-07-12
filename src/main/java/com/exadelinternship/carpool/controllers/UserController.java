package com.exadelinternship.carpool.controllers;


import com.exadelinternship.carpool.entity.User;
import com.exadelinternship.carpool.entity.dto.UserProfileDTO;
import com.exadelinternship.carpool.impl.UserServiceImpl;
import com.exadelinternship.carpool.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/main/{userId}")
    @ResponseBody
    public UserProfileDTO getUser(@PathVariable long userId){
        return convertToDto(userRepository.findById(userId));
    }

    private UserProfileDTO convertToDto(User user) {
        UserProfileDTO userDto = modelMapper.map(user, UserProfileDTO.class);
        return userDto;
    }

}
