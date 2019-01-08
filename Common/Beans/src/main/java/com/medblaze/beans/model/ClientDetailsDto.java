package com.medblaze.beans.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientDetailsDto {

    private String mbid;
    private String clientId;
    private String resourceIds;
    private String scopes;
    private String grantTypes;
    private String authorities;
    private int accessTokenTime;
    private int refreshTokenTime;
    private String addtionalInfo;
    private String redirectUrl;
    private boolean autoApprove;

}
