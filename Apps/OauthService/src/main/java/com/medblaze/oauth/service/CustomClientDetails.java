package com.medblaze.oauth.service;

import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import java.util.*;

@Setter
public class CustomClientDetails implements ClientDetails {

    private String clientId;
    private String clientSecret;
    private String resourceIds;
    private String scopes;
    private String grantType;
    private int accessTokenTime;
    private int refreshTokenTime;
    private boolean autoApprove;
    private List<GrantedAuthority> authorities;

    @Override
    public String getClientId() {
        return clientId;
    }

    @Override
    public Set<String> getResourceIds() {
        return getSet(resourceIds);
    }

    @Override
    public boolean isSecretRequired() {
        return true;
    }

    @Override
    public String getClientSecret() {
        return clientSecret;
    }

    @Override
    public boolean isScoped() {
        return true;
    }

    @Override
    public Set<String> getScope() {
        return getSet(scopes);
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        return getSet(grantType);
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return null;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return accessTokenTime;
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return refreshTokenTime;
    }

    @Override
    public boolean isAutoApprove(String s) {
        return autoApprove;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return null;
    }

    private Set<String> getSet(String data){
        if(data!=null){
            String [] arr = data.split(",");
            Set<String> set = new HashSet<String>(Arrays.asList(arr));
            return set;
        }
        return new HashSet<String>();
    }
}
