package com.medblaze.oauth.service;

import com.medblaze.beans.model.UserDto;
import com.medblaze.dbservice.UserService;
import com.medblaze.sqladapter.util.TenantUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Controller
public class CustomUserDetailService implements UserDetailsService {


    private final UserService userService;
    private final GrantAuthorityUtil grantAuthorityUtil;
    private final TenantUtil tenantUtil;

    @Autowired
    public CustomUserDetailService(UserService userService, GrantAuthorityUtil grantAuthorityUtil, TenantUtil tenantUtil) {
        this.userService = userService;
        this.grantAuthorityUtil = grantAuthorityUtil;
        this.tenantUtil = tenantUtil;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CustomUserDetails authUser = new CustomUserDetails();
        String [] arr = username.split("@");
        if(arr.length == 2 && tenantUtil.isValidTenant(arr[1])){
            UserDto user = userService.getUserByUsername(arr[0],arr[1]);
            if (user!=null){
                authUser.setActive(user.isActive());
                authUser.setUsername(username);
                authUser.setPassword(user.getPassword());
                authUser.setAuthorities(grantAuthorityUtil.getAuthorities(user.getRoles()));
                return authUser;
            }
            throw new UsernameNotFoundException("no such user is registered");
        }
        throw new UsernameNotFoundException("username is not in format username@tenantName or no such tenant");
    }

}
