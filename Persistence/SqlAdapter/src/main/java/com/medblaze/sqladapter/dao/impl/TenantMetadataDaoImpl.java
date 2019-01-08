package com.medblaze.sqladapter.dao.impl;

import com.medblaze.beans.model.TenantMetadataDto;
import com.medblaze.sqladapter.dao.TenantMetadataDao;
import com.medblaze.sqladapter.mapper.TenantMetadataMapper;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TenantMetadataDaoImpl implements TenantMetadataDao {


    private final Logger logger = Logger.getLogger(TenantMetadataDaoImpl.class);

    private final String GET_ALL = "select tenants.mbid, tenants.name, metadata.host, metadata.port, " +
            "metadata.username, metadata.password from tenants inner join metadata " +
            "on tenants.mbid = metadata.tenant_id ;";

    private final String GET_BY_NAME = "select tenants.mbid, tenants.name, metadata.host, metadata.port, " +
            "metadata.username, metadata.password from tenants inner join metadata " +
            "on tenants.mbid = metadata.tenant_id where name = ?;";


    @Override
    public List<TenantMetadataDto> getAll(JdbcTemplate jdbcTemplate) {
        return jdbcTemplate.query(GET_ALL, new TenantMetadataMapper());
    }

    @Override
    public TenantMetadataDto getByName(String tenantName, JdbcTemplate jdbcTemplate) {
        return jdbcTemplate.queryForObject(GET_BY_NAME, new Object[]{tenantName}, new TenantMetadataMapper());
    }
}
