package com.medblaze.sqladapter.mapper;

import com.medblaze.beans.model.ClientDto;
import com.medblaze.beans.model.UserDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientMapper implements RowMapper<ClientDto> {

    @Override
    public ClientDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        ClientDto client  = new ClientDto();
        client.setMbid(rs.getString("mbid"));
        client.setClientId(rs.getString("client_id"));
        client.setClientSecret(rs.getString("client_secret"));
        client.setAuthorities(rs.getString("authorities"));
        client.setGrantTypes(rs.getString("grant_types"));
        client.setRedirectUrl(rs.getString("redirect_uri"));
        client.setResourceIds(rs.getString("resource_ids"));
        client.setScopes(rs.getString("scopes"));
        client.setAccessTokenTime(rs.getInt("access_token_validity"));
        client.setRefreshTokenTime(rs.getInt("refresh_token_validity"));
        client.setAddtionalInfo(rs.getString("additional_information"));
        client.setAutoApprove(rs.getBoolean("autoapprove"));
        return client;
    }

}