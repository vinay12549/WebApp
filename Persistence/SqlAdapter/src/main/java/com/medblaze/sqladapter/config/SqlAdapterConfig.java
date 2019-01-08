package com.medblaze.sqladapter.config;

import com.medblaze.beans.model.TenantMetadataDto;
import com.medblaze.beans.util.UUIDUtil;
import com.medblaze.sqladapter.dao.TenantMetadataDao;
import com.medblaze.sqladapter.util.TenantUtil;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jndi.JndiTemplate;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
@ComponentScan(basePackages = "com.medblaze.sqladapter.dao.impl," +
        "com.medblaze.sqladapter.util")
@PropertySource(value = "classpath:database.properties",ignoreResourceNotFound = false)
public class SqlAdapterConfig {

    private final Environment env;

    private final String URL = "jdbc:postgresql://";
    private final String DRIVER_CLASS_NAME = "org.postgresql.Driver";
    private final String DEFAULT_TENANT_NAME = "default";
    private final String JNDI_LOOKUP = "java:comp/env/jdbc/postgres";

    @Autowired
    private TenantMetadataDao tenantMetadataDao;

    @Autowired
    public SqlAdapterConfig(Environment env) {
        this.env=env;
    }

    @Bean
    public UUIDUtil getUtil(){
        return new UUIDUtil();
    }

    @Bean
    public Set<String> getTenantsName() throws NamingException {
        Set<String> set = new HashSet<>();
        JdbcTemplate jdbcTemplate = getJdbcTemplate();
        List<TenantMetadataDto> list = tenantMetadataDao.getAll(jdbcTemplate);
        for(TenantMetadataDto dto : list){
            if(!set.contains(dto.getTenantName())){
                set.add(dto.getTenantName());
            }
        }
        set.add(DEFAULT_TENANT_NAME);
        return set;
    }

    @Bean
    public ConcurrentHashMap<String,JdbcTemplate> getConcurrentHashMap() throws NamingException {
        ConcurrentHashMap<String,JdbcTemplate> map = new ConcurrentHashMap<String,JdbcTemplate>();
        JdbcTemplate jdbcTemplate = getJdbcTemplate();
        List<TenantMetadataDto> list = tenantMetadataDao.getAll(jdbcTemplate);
        for(TenantMetadataDto dto : list){
            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setDriverClassName(DRIVER_CLASS_NAME);
            dataSource.setUsername(dto.getUsername());
            dataSource.setPassword(dto.getPassword());
            String dbName = dto.getTenantId().replace("-","");
            dataSource.setUrl(URL+ dto.getHost() +":"+dto.getPort() +"/med_"+dbName);
            dataSource.setMaxActive(20);
            dataSource.setMaxIdle(5);
            dataSource.setInitialSize(5);
            JdbcTemplate jdbc = new JdbcTemplate();
            jdbc.setDataSource(dataSource);
            map.put(dto.getTenantName(),jdbc);
        }
        map.put(DEFAULT_TENANT_NAME,jdbcTemplate);
        return map;
    }

    private JdbcTemplate getJdbcTemplate() throws NamingException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(getDataSource());
        return jdbcTemplate;
    }

    private DataSource getDataSource() throws NamingException {
        return (DataSource) new JndiTemplate().lookup(JNDI_LOOKUP);
    }

}
