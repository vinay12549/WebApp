package com.medblaze.oauth.service;

import com.medblaze.beans.model.ClientDto;
import com.medblaze.dbservice.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller("CustomClientDetailService")
public class CustomClientDetailService implements ClientDetailsService {

    private final ClientService service;
    private final GrantAuthorityUtil grantAuthorityUtil;

    @Autowired
    public CustomClientDetailService(ClientService service,GrantAuthorityUtil grantAuthorityUtil) {
        this.service = service;
        this.grantAuthorityUtil = grantAuthorityUtil;
    }

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        ClientDto clientDto = service.getByClientName(clientId);
        if(clientDto!=null){
            CustomClientDetails clientDetails = new CustomClientDetails();
            clientDetails.setClientId(clientId);
            clientDetails.setClientSecret(clientDto.getClientSecret());
            clientDetails.setAutoApprove(clientDto.isAutoApprove());
            clientDetails.setResourceIds(clientDto.getResourceIds());
            clientDetails.setScopes(clientDto.getScopes());
            clientDetails.setGrantType(clientDto.getGrantTypes());
            clientDetails.setAccessTokenTime(clientDto.getAccessTokenTime());
            clientDetails.setRefreshTokenTime(clientDto.getRefreshTokenTime());
            clientDetails.setAuthorities(grantAuthorityUtil.getAuthorities(clientDto.getAuthorities()));
            return clientDetails;
        }
        throw  new ClientRegistrationException("no such client registered");
    }

}
