package com.exadelinternship.carpool.dto;

import com.exadelinternship.carpool.entity.enums.UserRole;

import java.util.List;

public class UserInformationDTO {
    private String name;
    private UserRole role;
    private int amountOfNotifications;

    public int getAmountOfNotifications() {
        return amountOfNotifications;
    }

    public void setAmountOfNotifications(int amountOfNotifications) {
        this.amountOfNotifications = amountOfNotifications;
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
