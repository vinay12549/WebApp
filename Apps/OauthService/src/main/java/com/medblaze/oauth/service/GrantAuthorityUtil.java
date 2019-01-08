package com.medblaze.oauth.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class GrantAuthorityUtil {

    public List<GrantedAuthority> getAuthorities(String authorities){
        if(authorities!=null){
            String [] arr = authorities.split(",");
            List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
            for(String str : arr){
                list.add(new GrantAuthority(str));
            }
            return list;
        }
        return new ArrayList<GrantedAuthority>();
    }

}
