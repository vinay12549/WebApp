package com.medblaze.tenant.controllers;

import com.medblaze.appscommon.interceptor.RequestContextProvider;
import com.medblaze.beans.model.UserDto;
import com.medblaze.beans.request.Metadata;
import com.medblaze.beans.response.Response;
import com.medblaze.dbservice.UserService;
import com.medblaze.sqladapter.util.TenantUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private static final Logger logger = Logger.getLogger(UserController.class);

    private final UserService userService;
    private final BCryptPasswordEncoder encoder;
    private final TenantUtil tenantUtil;

    @Autowired
    public UserController(UserService userService,BCryptPasswordEncoder encoder, TenantUtil tenantUtil) {
        this.userService = userService;
        this.encoder = encoder;
        this.tenantUtil = tenantUtil;
    }

    @RequestMapping(method= RequestMethod.GET)
    public ResponseEntity<Response> getAllUsers(HttpServletRequest request) {
        isValidTenant();
        Response response =  new Response(null,null,userService.getAllUsers(RequestContextProvider.getTenantName()));
        return new ResponseEntity<Response>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/details", method= RequestMethod.GET)
    public ResponseEntity<Response> getUserDetails(HttpServletRequest request) {
        isValidTenant();
        Response response =  new Response(null,null,userService.getUserDetails(RequestContextProvider.getUsername(), RequestContextProvider.getTenantName()));
        return new ResponseEntity<Response>(response, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> createUser(@RequestBody() UserDto userDto, HttpServletRequest request){
        isValidTenant();
        userDto.setPassword(encoder.encode(userDto.getPassword()));
        Response response =  new Response(null,null,userService.createUser(userDto, RequestContextProvider.getTenantName()));
        return new ResponseEntity<Response>(response,HttpStatus.OK);
    }

    @RequestMapping(value = "/{userId}",method = RequestMethod.GET)
    public ResponseEntity<Response> getUserById(@PathVariable() String userId, HttpServletRequest request){
        isValidTenant();
        Response response =  new Response(null,null,userService.getUserById(userId, RequestContextProvider.getTenantName()));
        return new ResponseEntity<Response>(response,HttpStatus.OK);
    }

    @RequestMapping(value = "/{userId}",method = RequestMethod.PUT)
    public ResponseEntity<Response> disableUser(@PathVariable() String userId, HttpServletRequest request){
        isValidTenant();
        Response response =  new Response(null,null,userService.disableUser(userId, RequestContextProvider.getTenantName()));
        return new ResponseEntity<Response>(response,HttpStatus.OK);
    }

    @RequestMapping(value = "/metadata/{userId}",method = RequestMethod.PUT)
    public ResponseEntity<Response> updateUserMetadata(@PathVariable() String userId, @RequestBody Metadata metadata, HttpServletRequest request){
        isValidTenant();
        Response response =  new Response(null,null,userService.saveUserMetadat(metadata.getMetadata(), userId, RequestContextProvider.getTenantName()));
        return new ResponseEntity<Response>(response,HttpStatus.OK);
    }

    public void isValidTenant(){
        tenantUtil.isValidTenant(RequestContextProvider.getTenantName());
    }


}
