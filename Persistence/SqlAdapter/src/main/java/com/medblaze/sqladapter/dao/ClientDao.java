package com.medblaze.sqladapter.dao;

import com.medblaze.beans.model.ClientDetailsDto;
import com.medblaze.beans.model.ClientDto;

import java.util.List;

public interface ClientDao {

    ClientDto createClient(ClientDto clientDto);
    ClientDetailsDto getClientById(String clientId);
    ClientDto getUserByClientName(String clientName);
    List<ClientDetailsDto> getAllClients();

}

