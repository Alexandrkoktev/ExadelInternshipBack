package com.exadelinternship.carpool.entity.enums;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
    ROLE_COMMONUSER,
    ROLE_ADMINISTRATOR;
    @Override
    public String getAuthority()
    {
        return name();
    }
}
