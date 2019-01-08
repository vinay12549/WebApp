package com.medblaze.beans.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class MetadataDetailsDto {

    private String mbid;
    private String host;
    private String username;
    private int port;
    private String tenantId;

}
