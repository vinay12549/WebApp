package com.medblaze.dbservice.impl;

import com.medblaze.beans.model.UserDetailsDto;
import com.medblaze.beans.model.UserDto;
import com.medblaze.dbservice.UserService;
import com.medblaze.sqladapter.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDto createUser(UserDto userDto, String tenantName) {
        return userDao.createUser(userDto,tenantName);
    }

    @Override
    public UserDto getUserByUsername(String username, String tenantName) {
        return userDao.getUserByUsername(username,tenantName);
    }

    @Override
    public UserDetailsDto getUserDetails(String username, String tenantName) {
        return userDao.getUserDetails(username,tenantName);
    }

    @Override
    public UserDetailsDto getUserById(String userId, String tenantName) {
        return userDao.getUserById(userId,tenantName);
    }

    @Override
    public List<UserDetailsDto> getAllUsers(String tenantName) {
        return userDao.getAllUsers(tenantName);
    }



    @Override
    public boolean disableUser(String userId, String tenantName) {
        return userDao.disableUser(userId,tenantName);
    }

    @Override
    public boolean saveUserMetadat(String metadata, String userId, String tenantName) {
        return userDao.saveUserMetadata(metadata,userId,tenantName);
    }
}
