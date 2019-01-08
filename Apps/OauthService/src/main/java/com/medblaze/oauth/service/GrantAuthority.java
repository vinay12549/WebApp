package com.medblaze.oauth.service;

import org.springframework.security.core.GrantedAuthority;

public class GrantAuthority implements GrantedAuthority {

    private String authority;

    public GrantAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
