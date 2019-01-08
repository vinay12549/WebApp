package com.medblaze.dbservice;

import com.medblaze.beans.model.TenantDto;
import com.medblaze.beans.model.UserDetailsDto;
import com.medblaze.beans.model.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto, String tenantName);
    UserDetailsDto getUserById(String userId, String tenantName);
    UserDto getUserByUsername(String username, String tenantName);
    UserDetailsDto getUserDetails(String username, String tenantName);
    List<UserDetailsDto> getAllUsers(String tenantName);
    boolean disableUser(String userId, String tenantName);
    boolean saveUserMetadat(String metadata, String userId, String tenantName);

}
