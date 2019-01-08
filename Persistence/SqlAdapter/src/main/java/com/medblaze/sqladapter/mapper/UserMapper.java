package com.medblaze.sqladapter.mapper;

import com.medblaze.beans.model.UserDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<UserDto> {
    @Override
    public UserDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserDto user  = new UserDto();
        user.setMbid(rs.getString("mbid"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setRoles(rs.getString("roles"));
        user.setEmail(rs.getString("email"));
        user.setMobile(rs.getString("mobile"));
        user.setActive(rs.getBoolean("isactive"));
        user.setMetadata(rs.getString("metadata"));
        return user;
    }
}
