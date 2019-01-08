package com.medblaze.sqladapter.mapper;

import com.medblaze.beans.model.MetadataDto;
import com.medblaze.beans.model.TenantDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MetadataMapper implements RowMapper<MetadataDto> {
    @Override
    public MetadataDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        MetadataDto metadataDto = new MetadataDto();
        metadataDto.setMbid(rs.getString("mbid"));
        metadataDto.setHost(rs.getString("host"));
        metadataDto.setPort(rs.getInt("port"));
        metadataDto.setUsername(rs.getString("username"));
        metadataDto.setPassword(rs.getString("password"));
        metadataDto.setTenantId(rs.getString("tenant_id"));
        return metadataDto;
    }
}
