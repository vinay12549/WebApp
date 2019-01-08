package com.medblaze.sqladapter.dao.impl;

import com.medblaze.beans.model.ClientDetailsDto;
import com.medblaze.beans.model.ClientDto;
import com.medblaze.beans.util.UUIDUtil;
import com.medblaze.sqladapter.dao.ClientDao;
import com.medblaze.sqladapter.mapper.ClientDetailsMapper;
import com.medblaze.sqladapter.mapper.ClientMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ClientDaoImpl implements ClientDao {

    private final Logger logger = Logger.getLogger(ClientDaoImpl.class);

    private final ConcurrentHashMap<String,JdbcTemplate> map;
    private final UUIDUtil util;
    private final String DEFAULT_TENANT_NAME = "default";
    private final String GET_BY_ID = "select * from clients where mbid = ?";
    private final String GET_BY_CLIENTNAME = "select * from clients where client_id = ?";
    private final String GET_ALL = "select * from clients ";
    private final String INSERT = "insert into clients (mbid, client_id, client_secret, resource_ids, scopes," +
            " grant_types, redirect_uri, authorities, additional_information, autoapprove, access_token_validity," +
            " refresh_token_validity) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";


    @Autowired
    public ClientDaoImpl(ConcurrentHashMap<String,JdbcTemplate> map, UUIDUtil util) {
        this.map = map;
        this.util = util;
    }

    @Override
    public ClientDto createClient(ClientDto c) {
        c.setMbid(util.generateId());
        map.get(DEFAULT_TENANT_NAME).update(INSERT,c.getMbid(), c.getClientId(), c.getClientSecret(), c.getResourceIds(), c.getScopes(),
        c.getGrantTypes(), c.getRedirectUrl(), c.getAuthorities(), c.getAddtionalInfo(), c.isAutoApprove(),
                c.getAccessTokenTime(), c.getRefreshTokenTime());
        c.setClientSecret(null);
        return c;
    }

    @Override
    public ClientDetailsDto getClientById(String clientId) {
        try{
            return map.get(DEFAULT_TENANT_NAME).queryForObject(GET_BY_ID, new Object[]{clientId}, new ClientDetailsMapper());
        }catch (EmptyResultDataAccessException e){
            logger.error(e.toString());
        }
        return null;
    }

    @Override
    public ClientDto getUserByClientName(String clientName) {
        try{
            return map.get(DEFAULT_TENANT_NAME).queryForObject(GET_BY_CLIENTNAME, new Object[]{clientName}, new ClientMapper());
        }catch (EmptyResultDataAccessException e){
            logger.error(e.toString());
        }
        return null;
    }

    @Override
    public List<ClientDetailsDto> getAllClients() {
        return map.get(DEFAULT_TENANT_NAME).query(GET_ALL, new ClientDetailsMapper());
    }
}
