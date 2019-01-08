package com.medblaze.sqladapter.dao.impl;

import com.medblaze.beans.model.MetadataDto;
import com.medblaze.beans.util.UUIDUtil;
import com.medblaze.sqladapter.dao.MetadataDao;
import com.medblaze.sqladapter.mapper.MetadataMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MetadataDaoImpl implements MetadataDao {

    private final Logger logger = Logger.getLogger(MetadataDaoImpl.class);

    private final ConcurrentHashMap<String,JdbcTemplate> map;
    private final UUIDUtil util;
    private final String DEFAULT_TENANT_NAME = "default";
    private final String GET_BY_TENANT_ID = "select * from metadata where tenant_id = ?";
    private final String DELETE_BY_TENANT_ID = "delete from metadata where tenant_id = ?";
    private final String INSERT = "insert into metadata (mbid, host, port, username, password, tenant_id) " +
            "VALUES (?, ?, ?, ?, ?, ?)";
    private final String UPDATE_BY_TENANT_ID = "update metadata set host=?, port=?, username=?, password=? where tenant_id = ?";

    @Autowired
    public MetadataDaoImpl(ConcurrentHashMap<String,JdbcTemplate> map, UUIDUtil util) {
        this.util=util;
        this.map=map;
    }

    @Override
    public MetadataDto createMetadata(MetadataDto metadataDto) {
        metadataDto.setMbid(util.generateId());
        boolean res = map.get(DEFAULT_TENANT_NAME).update(INSERT, metadataDto.getMbid(), metadataDto.getHost(), metadataDto.getPort(),
                metadataDto.getUsername(),metadataDto.getPassword(),metadataDto.getTenantId()) > 0;
        if(res)
            return metadataDto;
        else
            return null;
    }

    @Override
    public MetadataDto updateMetadata(MetadataDto metadataDto) {
        boolean res = map.get(DEFAULT_TENANT_NAME).update(UPDATE_BY_TENANT_ID, metadataDto.getHost(), metadataDto.getPort(),
                metadataDto.getUsername(),metadataDto.getPassword(),metadataDto.getTenantId()) > 0;
        if(res)
            return metadataDto;
        else
            return null;
    }

    @Override
    public MetadataDto getMetadata(String tenantId) {
        try{
            return map.get(DEFAULT_TENANT_NAME).queryForObject(GET_BY_TENANT_ID, new Object[]{tenantId}, new MetadataMapper());
        }catch (EmptyResultDataAccessException e){
            logger.error(e.toString());
        }
        return null;
    }

    @Override
    public boolean deleteMetadata(String tenantId) {
        return map.get(DEFAULT_TENANT_NAME).update(DELETE_BY_TENANT_ID,tenantId)>0;
    }
}
