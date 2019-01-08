package com.medblaze.sqladapter.dao;

import com.medblaze.beans.model.TenantDto;

import java.sql.SQLException;
import java.util.List;

public interface TenantDao {

    TenantDto createTenant(TenantDto tenantDto);
    TenantDto updateTenant(TenantDto tenantDto);
    boolean initialize(String tenantId) throws SQLException;
    TenantDto getTenant(String tenantId);
    TenantDto getTenantByName(String tenantName);
    List<TenantDto> getAllTenant();
    boolean deleteTenant(String tenantId);
    boolean disableTenant(String tenantId);

}
