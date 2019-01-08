package com.medblaze.dbservice.impl;

import com.medblaze.beans.model.ClientDetailsDto;
import com.medblaze.beans.model.ClientDto;
import com.medblaze.dbservice.ClientService;
import com.medblaze.sqladapter.dao.ClientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientDao clientDao;

    @Autowired
    public ClientServiceImpl(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    @Override
    public ClientDto createClient(ClientDto clientDto) {
        return clientDao.createClient(clientDto);
    }

    @Override
    public ClientDetailsDto getClientById(String clientId) {
        return clientDao.getClientById(clientId);
    }

    @Override
    public ClientDto getByClientName(String clientName) {
        return clientDao.getUserByClientName(clientName);
    }

    @Override
    public List<ClientDetailsDto> getAllClients() {
        return clientDao.getAllClients();
    }

}
