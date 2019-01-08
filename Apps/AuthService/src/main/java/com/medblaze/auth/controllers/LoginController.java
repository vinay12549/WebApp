package com.medblaze.auth.controllers;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "",produces = MediaType.APPLICATION_JSON_VALUE)
public class LoginController {

    @RequestMapping(value = "/oauth/token?grant_type=password", method = RequestMethod.POST)
    public String getAccessToken(@RequestParam(value = "username") String username,
                                 @RequestParam(value = "password") String password,
                                 @RequestHeader(value = "Authorization") String authorization){
        return null;
    }

    @RequestMapping(value = "/oauth/token?grant_type=client_credentials", method = RequestMethod.POST)
    public String getAccessToken(@RequestHeader(value = "Authorization") String authorization){
        return null;
    }


}
