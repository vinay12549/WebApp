package com.medblaze.beans.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDetailsDto {

    private String mbid;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String mobile;
    private String roles;
    private boolean isActive;
    private String metadata;

}
