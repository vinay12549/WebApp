package com.medblaze.sqladapter.dao;

import com.medblaze.beans.model.TenantMetadataDto;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public interface TenantMetadataDao {

     List<TenantMetadataDto> getAll(JdbcTemplate jdbcTemplate);
     TenantMetadataDto getByName(String tenantName, JdbcTemplate jdbcTemplate);

}
