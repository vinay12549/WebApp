package com.medblaze.sqladapter.mapper;

import com.medblaze.beans.model.TenantDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TenantMapper implements RowMapper<TenantDto> {

    @Override
    public TenantDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        TenantDto tenantDto = new TenantDto();
        tenantDto.setMbid(rs.getString("mbid"));
        tenantDto.setActive(rs.getBoolean("isactive"));
        tenantDto.setInit(rs.getBoolean("init"));
        tenantDto.setName(rs.getString("name"));
        tenantDto.setDescription(rs.getString("description"));
        tenantDto.setTenantType(rs.getString("tenant_type"));
        tenantDto.setOwnerEmail(rs.getString("owner_email"));
        tenantDto.setOwnerName(rs.getString("owner_name"));
        return tenantDto;
    }

}
