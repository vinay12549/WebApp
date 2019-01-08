package com.medblaze.sqladapter.util;

import com.medblaze.beans.exception.NoSuchTenant;
import com.medblaze.beans.model.TenantDto;
import com.medblaze.beans.model.TenantMetadataDto;
import com.medblaze.beans.util.Constants;
import com.medblaze.sqladapter.dao.TenantMetadataDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class TenantUtil {

    private final ConcurrentHashMap<String,JdbcTemplate> map;
    private final TenantMetadataDao tenantMetadataDao;

    @Autowired
    public TenantUtil(ConcurrentHashMap<String,JdbcTemplate> map, TenantMetadataDao tenantMetadataDao) {
        this.map = map;
        this.tenantMetadataDao = tenantMetadataDao;
    }

    public boolean isValidTenant(String tenantName){
        boolean res = map.containsKey(tenantName);
        if(res){
            return res;
        }else {
            if(addTenantToJdbcTemplateMap(tenantName)){
                return true;
            }
        }
        throw new NoSuchTenant("tenant name is not valid .");
    }

    private boolean addTenantToJdbcTemplateMap(String tenantName){

        TenantMetadataDto dto = tenantMetadataDao.getByName(tenantName,map.get(Constants.DEFAULT_TENANT_NAME));
        if(dto!=null){
            map.put(tenantName,getJdbcTemplate(dto));
            return true;
        }
        return false;

    }

    public JdbcTemplate getJdbcTemplate(TenantMetadataDto dto ){
        String dbName = dto.getTenantId().replace("-","");

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(Constants.URL_PREFIX+dto.getHost()+Constants.COLLON+dto.getPort()+"/"+Constants.DB_PREFIX+dbName);
        dataSource.setDriverClassName(Constants.DRIVER_CLASS_NAME);
        dataSource.setUsername(dto.getUsername());
        dataSource.setPassword(dto.getPassword());
        JdbcTemplate jdbc = new JdbcTemplate();
        jdbc.setDataSource(dataSource);
        return jdbc;
    }

}
