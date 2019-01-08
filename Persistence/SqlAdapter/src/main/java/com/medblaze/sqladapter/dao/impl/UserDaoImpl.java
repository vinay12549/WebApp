package com.medblaze.sqladapter.dao.impl;

import com.medblaze.beans.model.UserDetailsDto;
import com.medblaze.beans.model.UserDto;
import com.medblaze.beans.util.UUIDUtil;
import com.medblaze.sqladapter.dao.UserDao;
import com.medblaze.sqladapter.mapper.UserDetailsMapper;
import com.medblaze.sqladapter.mapper.UserMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;


@Repository
public class UserDaoImpl implements UserDao {


    private final Logger logger = Logger.getLogger(UserDaoImpl.class);

    private final ConcurrentHashMap<String,JdbcTemplate> map;
    private final UUIDUtil util;
    private final String GET_BY_ID = "select * from users where mbid = ?";
    private final String GET_BY_USERNAME = "select * from users where username = ?";
    private final String GET_ALL = "select * from users ";
    private final String DISABLE_BY_ID = "update users set isactive = ? where mbid = ?";
    private final String UPDATE_METADATA_BY_ID = "update users set metadata = ? where mbid = ?";
    private final String INSERT = "insert into users (mbid, first_name, last_name, username, password, roles, email, mobile, isactive, metadata) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";


    @Autowired
    public UserDaoImpl(ConcurrentHashMap<String,JdbcTemplate> map, UUIDUtil util) {
        this.util=util;
        this.map=map;
    }

    @Override
    public UserDto getUserByUsername(String username, String tenantName) {
        try{
            return map.get(tenantName).queryForObject(GET_BY_USERNAME, new Object[]{username}, new UserMapper());
        }catch (EmptyResultDataAccessException e){
            logger.error(e.toString());
        }
        return null;
    }

    @Override
    public UserDetailsDto getUserDetails(String username, String tenantName) {
        try{
            return map.get(tenantName).queryForObject(GET_BY_USERNAME, new Object[]{username}, new UserDetailsMapper());
        }catch (EmptyResultDataAccessException e){
            logger.error(e.toString());
        }
        return null;
    }

    @Override
    public UserDto createUser(UserDto u, String tenantName) {
        u.setMbid(util.generateId());
        map.get(tenantName).update(INSERT,u.getMbid(),u.getFirstName(),u.getLastName(),u.getUsername(),u.getPassword(),
                u.getRoles(),u.getEmail(),u.getMobile(),u.isActive(),u.getMetadata());
        u.setPassword(null);
        return u;
    }

    @Override
    public UserDetailsDto getUserById(String userId, String tenantName) {
        try{
            return map.get(tenantName).queryForObject(GET_BY_ID, new Object[]{userId}, new UserDetailsMapper());
        }catch (EmptyResultDataAccessException e){
            logger.error(e.toString());
        }
        return null;
    }

    @Override
    public List<UserDetailsDto> getAllUsers(String tenantName) {
        return map.get(tenantName).query(GET_ALL, new UserDetailsMapper());
    }

    @Override
    public boolean disableUser(String userId, String tenantName) {
        return map.get(tenantName).update(DISABLE_BY_ID, false, userId) > 0;
    }

    @Override
    public boolean saveUserMetadata(String metadata, String userId, String tenantName) {
        return map.get(tenantName).update(UPDATE_METADATA_BY_ID, metadata, userId) > 0;
    }
}
