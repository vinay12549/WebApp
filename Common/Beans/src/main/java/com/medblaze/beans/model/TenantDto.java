package com.medblaze.beans.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class TenantDto {

    private String mbid;
    private String name;
    private String description;
    private String tenantType;
    private String ownerEmail;
    private String ownerName;
    private boolean isActive;
    private boolean init;

}
