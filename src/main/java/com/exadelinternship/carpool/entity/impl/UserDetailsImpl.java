package com.exadelinternship.carpool.entity.impl;

import com.exadelinternship.carpool.entity.enums.UserRole;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;

public class UserDetailsImpl implements UserDetails {
    private String password;
    private String username;
    private long id;
    private Set<UserRole> roles;

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<UserRole> getRole() {
        return roles;
    }

    public void setRole(Set<UserRole> roles) {
        this.roles = roles;
    }

    public Set<UserRole> getAuthorities (){
        return roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
