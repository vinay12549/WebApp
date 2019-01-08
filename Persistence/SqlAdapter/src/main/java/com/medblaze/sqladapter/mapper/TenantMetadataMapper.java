package com.medblaze.sqladapter.mapper;

import com.medblaze.beans.model.TenantMetadataDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TenantMetadataMapper implements RowMapper<TenantMetadataDto> {


    @Override
    public TenantMetadataDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        TenantMetadataDto dto = new TenantMetadataDto();
        dto.setTenantName(rs.getString("name"));
        dto.setTenantId(rs.getString("mbid"));
        dto.setHost(rs.getString("host"));
        dto.setPassword(rs.getString("password"));
        dto.setPort(rs.getInt("port"));
        dto.setUsername(rs.getString("username"));
        return dto;
    }

}
