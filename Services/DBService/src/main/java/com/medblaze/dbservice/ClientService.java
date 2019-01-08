package com.medblaze.dbservice;

import com.medblaze.beans.model.ClientDetailsDto;
import com.medblaze.beans.model.ClientDto;
import com.medblaze.beans.model.UserDto;

import java.util.List;

public interface ClientService {

    ClientDto createClient(ClientDto clientDto);
    ClientDetailsDto getClientById(String clientId);
    ClientDto getByClientName(String clientName);
    List<ClientDetailsDto> getAllClients();

}
