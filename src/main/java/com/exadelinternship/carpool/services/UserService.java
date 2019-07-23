package com.exadelinternship.carpool.services;

import com.exadelinternship.carpool.adapters.UserAdapter;
import com.exadelinternship.carpool.dto.UserInformationDTO;
import com.exadelinternship.carpool.dto.UserProfileDTO;
import com.exadelinternship.carpool.entity.User;
import com.exadelinternship.carpool.entity.impl.UserDetailsImpl;
import com.exadelinternship.carpool.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserAdapter userAdapter;

    @Autowired
    private UserRepository userRepository;

    public UserInformationDTO getUserInformation(){
        UserDetailsImpl principal = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> user = userRepository.findById(principal.getId());
        if(user.isPresent()){
            return userAdapter.userToUserInformationDto(user.get());
        } else{
            return null;
        }
    }

    public UserProfileDTO getProfile(){
        UserDetailsImpl principal = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> user = userRepository.findById(principal.getId());
        if(user.isPresent()){
            return userAdapter.userToUserProfileDTO(user.get());
        } else{
            return null;
        }
    }

    public UserDetailsImpl loadUserByUsername(String login){
        Optional<User> user = userRepository.findByLogin(login);
        if(user!=null){
            return userAdapter.userToUserDetail(user.get());
        }
        else{
            return null;
        }
    }

}
