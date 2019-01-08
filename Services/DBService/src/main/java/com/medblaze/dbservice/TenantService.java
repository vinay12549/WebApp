package com.medblaze.dbservice;

import com.medblaze.beans.model.TenantDto;

import java.sql.SQLException;
import java.util.List;

public interface TenantService {

    TenantDto createTenant(TenantDto tenantDto);
    TenantDto updateTenant(TenantDto tenantDto);
    boolean initTenant(String tenantId) throws SQLException;
    TenantDto getTenant(String tenantId);
    List<TenantDto> getAllTenant();
    boolean deleteTenant(String tenantId);
    boolean disableTenant(String tenantId);
}
