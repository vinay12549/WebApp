package com.medblaze.appscommon.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.HashMap;

@Controller
public class JwtUtil {

    private final ObjectMapper objectMapper;

    @Autowired
    public JwtUtil(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public String[] getTenantName(String jwt) throws IOException {
        String authToken[] = jwt.split(" ");
        Jwt jwtToken = JwtHelper.decode(authToken[1]);
        String claims = jwtToken.getClaims();
        HashMap claimsMap = objectMapper.readValue(claims, HashMap.class);
        String arr = (String)claimsMap.get("user_name");
        String [] username = arr.split("@");
        return username;
    }

}
