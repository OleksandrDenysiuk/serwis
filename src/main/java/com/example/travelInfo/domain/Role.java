package com.example.travelInfo.domain;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER,ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }

    public Role getAdmin(){
        return ADMIN;
    }
}
