package com.medblaze.tenant.exceptions;


import com.medblaze.beans.exception.NoSuchTenant;
import com.medblaze.beans.response.Response;
import com.medblaze.tenant.controllers.TenantController;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = Logger.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handleException(Exception e){
        logger.error(e.toString());
        Response response = new Response("INTERNAL_SERVER_ERROR", e.getMessage(),null);
        return new ResponseEntity<Response>(response,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NoSuchTenant.class)
    public ResponseEntity<Response> handleNOSuchTenantException(NoSuchTenant e){
        logger.error(e.toString());
        Response response = new Response("BAD_REQUEST", e.getMessage(),null);
        return new ResponseEntity<Response>(response,HttpStatus.BAD_REQUEST);
    }


}
