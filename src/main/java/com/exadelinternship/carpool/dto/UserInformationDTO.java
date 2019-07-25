package com.exadelinternship.carpool.dto;

import com.exadelinternship.carpool.entity.enums.UserRole;

import java.util.List;

public class UserInformationDTO {
    private String name;
    private UserRole role;
    private boolean allNotificationsChecked;

    public boolean isAllNotificationsChecked() {
        return allNotificationsChecked;
    }

    public void setAllNotificationsChecked(boolean allNotificationsChecked) {
        this.allNotificationsChecked = allNotificationsChecked;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
