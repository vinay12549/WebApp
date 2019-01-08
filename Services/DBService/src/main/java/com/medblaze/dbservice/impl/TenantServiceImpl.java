package com.medblaze.dbservice.impl;

import com.medblaze.beans.model.MetadataDto;
import com.medblaze.beans.model.TenantDto;
import com.medblaze.dbservice.TenantService;
import com.medblaze.sqladapter.dao.MetadataDao;
import com.medblaze.sqladapter.dao.TenantDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
public class TenantServiceImpl implements TenantService {

    private final TenantDao tenantDao;
    private final MetadataDao metadataDao;



    @Autowired
    public TenantServiceImpl(TenantDao tenantDao, MetadataDao metadataDao) {
        this.tenantDao=tenantDao;
        this.metadataDao=metadataDao;
    }

    @Override
    public boolean initTenant(String tenantId) throws SQLException {
        return tenantDao.initialize(tenantId);
    }

    @Override
    public TenantDto createTenant(TenantDto tenantDto) {
        return tenantDao.createTenant(tenantDto);
    }

    @Override
    public TenantDto updateTenant(TenantDto tenantDto) {
        return tenantDao.updateTenant(tenantDto);
    }

    @Override
    public TenantDto getTenant(String tenantId) {
        return tenantDao.getTenant(tenantId);
    }

    @Override
    public List<TenantDto> getAllTenant() {
        return tenantDao.getAllTenant();
    }

    @Transactional
    @Override
    public boolean deleteTenant(String tenantId) {
        metadataDao.deleteMetadata(tenantId);
        return tenantDao.deleteTenant(tenantId);
    }

    @Override
    public boolean disableTenant(String tenantId) {
        return tenantDao.disableTenant(tenantId);
    }

}
