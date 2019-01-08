package com.medblaze.sqladapter.dao.impl;

import com.medblaze.beans.model.MetadataDto;
import com.medblaze.beans.model.TenantDto;
import com.medblaze.beans.util.Constants;
import com.medblaze.beans.util.UUIDUtil;
import com.medblaze.sqladapter.dao.MetadataDao;
import com.medblaze.sqladapter.dao.TenantDao;
import com.medblaze.sqladapter.mapper.TenantMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class TenantDaoImpl implements TenantDao {

    private final Logger logger = Logger.getLogger(TenantDaoImpl.class);

    private final ConcurrentHashMap<String,JdbcTemplate> map;
    private final MetadataDao metadataDao;
    private final UUIDUtil util;

    private final String GET_ALL = "select * from tenants";
    private final String GET_BY_ID = "select * from tenants where mbid = ?";
    private final String GET_BY_NAME = "select * from tenants where name = ?";
    private final String DELETE_BY_ID = "delete from tenants where mbid = ?";
    private final String DISABLE_BY_ID = "update tenants set isactive = ? where mbid = ?";
    private final String INIT_BY_ID = "update tenants set init = ? where mbid = ?";
    private final String UPDATE_BY_ID = "update tenants set name = ?, description = ?, tenant_type = ?, owner_name = ?, owner_email = ?, isactive = ? where mbid = ?";
    private final String INSERT = "insert into tenants (mbid, name, description, tenant_type, owner_name, owner_email, isactive, init) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";



    @Autowired
    public TenantDaoImpl(ConcurrentHashMap<String,JdbcTemplate> map, UUIDUtil util, MetadataDao metadataDao) {
        this.util = util;
        this.map = map;
        this.metadataDao = metadataDao;
    }

    @Override
    public TenantDto updateTenant(TenantDto tenantDto) {
        map.get(Constants.DEFAULT_TENANT_NAME).update(UPDATE_BY_ID, tenantDto.getName(), tenantDto.getDescription(), tenantDto.getTenantType(), tenantDto.getOwnerName(), tenantDto.getOwnerEmail(), tenantDto.isActive(), tenantDto.getMbid());
        return getTenant(tenantDto.getMbid());
    }

    @Override
    public TenantDto createTenant(TenantDto tenantDto) {
        tenantDto.setMbid(util.generateId());
        map.get(Constants.DEFAULT_TENANT_NAME).update(INSERT, tenantDto.getMbid(), tenantDto.getName(), tenantDto.getDescription(),
                tenantDto.getTenantType(),tenantDto.getOwnerName(),tenantDto.getOwnerEmail(),tenantDto.isActive(), false);
        return tenantDto;
    }

    @Transactional
    @Override
    public boolean initialize(String tenantId) throws SQLException {

        MetadataDto metadataDto = metadataDao.getMetadata(tenantId);
        if (metadataDto!=null){

            String dbName = tenantId.replace("-","");
            String createDb = String.format(Constants.CREATE_DB,dbName);
            map.get(Constants.DEFAULT_TENANT_NAME).execute(createDb);

            DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setUrl(Constants.URL_PREFIX+metadataDto.getHost()+Constants.COLLON+metadataDto.getPort()+"/"+Constants.DB_PREFIX+dbName);
            dataSource.setDriverClassName(Constants.DRIVER_CLASS_NAME);
            dataSource.setUsername(metadataDto.getUsername());
            dataSource.setPassword(metadataDto.getPassword());
            JdbcTemplate jdbc = new JdbcTemplate();
            jdbc.setDataSource(dataSource);
            Resource resource = new ClassPathResource("tenant.sql");
            ScriptUtils.executeSqlScript(dataSource.getConnection(), resource);
            init(tenantId);
            return true;
        }
        return false;

    }

    private void init(String tenantId) {
        map.get(Constants.DEFAULT_TENANT_NAME).update(INIT_BY_ID, true, tenantId);
    }

    @Override
    public TenantDto getTenant(String tenantId) {
        try{
            return map.get(Constants.DEFAULT_TENANT_NAME).queryForObject(GET_BY_ID, new Object[]{tenantId}, new TenantMapper());
        }catch (EmptyResultDataAccessException e){
            logger.error(e.toString());
        }
        return null;
    }

    @Override
    public TenantDto getTenantByName(String tenantName) {
        try{
            return map.get(Constants.DEFAULT_TENANT_NAME).queryForObject(GET_BY_NAME, new Object[]{tenantName}, new TenantMapper());
        }catch (EmptyResultDataAccessException e){
            logger.error(e.toString());
        }
        return null;
    }

    @Override
    public List<TenantDto> getAllTenant() {
        return map.get(Constants.DEFAULT_TENANT_NAME).query(GET_ALL, new TenantMapper());
    }

    @Override
    public boolean deleteTenant(String tenantId) {
        return map.get(Constants.DEFAULT_TENANT_NAME).update(DELETE_BY_ID, tenantId) > 0;
    }

    @Override
    public boolean disableTenant(String tenantId) {
        return map.get(Constants.DEFAULT_TENANT_NAME).update(DISABLE_BY_ID, false, tenantId) > 0;
    }

}
