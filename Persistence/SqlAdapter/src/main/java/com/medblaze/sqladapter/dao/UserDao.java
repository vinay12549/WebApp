package com.medblaze.sqladapter.dao;

import com.medblaze.beans.model.UserDetailsDto;
import com.medblaze.beans.model.UserDto;

import java.util.List;

public interface UserDao {

    UserDto createUser(UserDto userDto, String tenantName);
    UserDetailsDto getUserById(String userId, String tenantName);
    UserDto getUserByUsername(String username, String tenantName);
    UserDetailsDto getUserDetails(String username, String tenantName);
    List<UserDetailsDto> getAllUsers(String tenantName);
    boolean disableUser(String userId, String tenantName);
    boolean saveUserMetadata(String metadata, String userId, String tenantName);

}
