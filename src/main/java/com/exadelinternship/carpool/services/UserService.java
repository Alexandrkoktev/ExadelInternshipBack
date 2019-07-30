package com.exadelinternship.carpool.services;

import com.exadelinternship.carpool.adapters.UserAdapter;
import com.exadelinternship.carpool.dto.UserInformationDTO;
import com.exadelinternship.carpool.dto.UserListsDTO;
import com.exadelinternship.carpool.dto.UserProfileDTO;
import com.exadelinternship.carpool.dto.UserStatisticDTO;
import com.exadelinternship.carpool.entity.User;
import com.exadelinternship.carpool.entity.impl.UserDetailsImpl;
import com.exadelinternship.carpool.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserAdapter userAdapter;

    @Autowired
    private UserRepository userRepository;

    private final int PAGE_SIZE = 50;

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

    public UserListsDTO getLists(){
        UserDetailsImpl principal = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> user = userRepository.findById(principal.getId());
        if(user.isPresent()){
            return userAdapter.userToUserListDTO(user.get());
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

    public List<UserStatisticDTO> getUsersStatistic(int mode, boolean order, int page, String name){
        String[] words = name.split("[ ]+");
        List<User> users = userRepository.findAll().stream()
                .filter(x->contains(x.getName(),words))
                .sorted(getComparator(mode, order))
                .skip(PAGE_SIZE*page)
                .limit(PAGE_SIZE)
                .collect(Collectors.toList());
        return usersToUsersStatistic(users);
    }

    private boolean contains(String word, String[] words){
        return Arrays.stream(words).allMatch(x->word.contains(x));
    }

    private Comparator<User> getComparator(int mode, boolean order){
        Comparator<User> result;
        switch (mode){
            case 0:
                result = new Comparator<User>() {
                    private boolean orderUp = order;
                    @Override
                    public int compare(User o1, User o2) {
                        return orderUp?o1.getName().compareToIgnoreCase(o2.getName()):o2.getName().compareToIgnoreCase(o1.getName());
                    }
                };
                break;
            case 1:
                result = new Comparator<User>() {
                    private boolean orderUp = order;
                    @Override
                    public int compare(User o1, User o2) {
                        return !orderUp?new Double(o1.getRatingDriver()).compareTo(o2.getRatingDriver()):
                                new Double(o2.getRatingDriver()).compareTo(o1.getRatingDriver());
                    }
                };
                break;
            case 2:
                result = new Comparator<User>() {
                    private boolean orderUp = order;
                    @Override
                    public int compare(User o1, User o2) {
                        return !orderUp?new Double(o1.getRatingPassenger()).compareTo(o2.getRatingPassenger()):
                                new Double(o2.getRatingPassenger()).compareTo(o1.getRatingPassenger());
                    }
                };
                break;
            case 3:
                result = new Comparator<User>() {
                    private boolean orderUp = order;
                    @Override
                    public int compare(User o1, User o2) {
                        return !orderUp?new Double(o1.getDistance()).compareTo(o2.getDistance()):
                                new Double(o2.getDistance()).compareTo(o1.getDistance());
                    }
                };
                break;
            case 4:
                result = new Comparator<User>() {
                    private boolean orderUp = order;
                    @Override
                    public int compare(User o1, User o2) {
                        return !orderUp?new Integer(o1.getAmountOfPassengers()).compareTo(o2.getAmountOfPassengers()):
                                new Integer(o2.getAmountOfPassengers()).compareTo(o1.getAmountOfPassengers());
                    }
                };
                break;
            case 5:
                result = new Comparator<User>() {
                    private boolean orderUp = order;
                    @Override
                    public int compare(User o1, User o2) {
                        return !orderUp?new Integer(o1.getAmountOfBookings()).compareTo(o2.getAmountOfBookings()):
                                new Integer(o2.getAmountOfBookings()).compareTo(o1.getAmountOfBookings());
                    }
                };
                break;
            case 6:
                result = new Comparator<User>() {
                    private boolean orderUp = order;
                    @Override
                    public int compare(User o1, User o2) {
                        return !orderUp?new Integer(o1.getAmountOfRoutes()).compareTo(o2.getAmountOfRoutes()):
                                new Integer(o2.getAmountOfRoutes()).compareTo(o1.getAmountOfRoutes());
                    }
                };
                break;
                default:
                    result = null;


        }
        return result;
    }

    private List<UserStatisticDTO> usersToUsersStatistic(List<User> users){
        List<UserStatisticDTO> usersStatistic = new ArrayList<>();
        users.stream()
                .forEach(x->usersStatistic.add(userAdapter.userToUserStatisticDTO(x)));
        return usersStatistic;
    }
}
