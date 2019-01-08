package com.medblaze.beans.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {

    public Response(String error, String error_description, Object data) {
        this.error = error;
        this.error_description = error_description;
        this.data = data;
    }

    private String error;
    private String error_description;
    private Object data;
}
