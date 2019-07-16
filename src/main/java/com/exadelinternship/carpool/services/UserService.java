package com.exadelinternship.carpool.services;

import com.exadelinternship.carpool.adapters.UserAdapter;
import com.exadelinternship.carpool.dto.UserAuthentificationDTO;
import com.exadelinternship.carpool.dto.UserInformationDTO;
import com.exadelinternship.carpool.entity.User;
import com.exadelinternship.carpool.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserAdapter userAdapter;

    @Autowired
    private UserRepository userRepository;

    public UserInformationDTO getUserInformation(UserAuthentificationDTO userAuthentificationDTO){
        User user = userRepository.findByLogin(userAuthentificationDTO.getLogin()).get();
        return userAdapter.userToUserInformationDto(user);
    }
}
