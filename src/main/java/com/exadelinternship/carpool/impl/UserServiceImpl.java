package com.exadelinternship.carpool.impl;

import com.exadelinternship.carpool.entity.User;
import com.exadelinternship.carpool.repository.UserRepository;
import com.exadelinternship.carpool.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User getById(long id){
        return userRepository.findById(id);
    }
}
