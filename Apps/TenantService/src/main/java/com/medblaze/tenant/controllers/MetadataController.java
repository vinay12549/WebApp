package com.medblaze.tenant.controllers;


import com.medblaze.beans.model.MetadataDto;
import com.medblaze.beans.model.TenantDto;
import com.medblaze.beans.response.Response;
import com.medblaze.dbservice.MetadataService;
import com.medblaze.dbservice.TenantService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/metadata", produces = MediaType.APPLICATION_JSON_VALUE)
public class MetadataController {

    private static final Logger logger = Logger.getLogger(MetadataController.class);

    private final MetadataService metadataService;

    @Autowired
    public MetadataController(MetadataService metadataService) {
        this.metadataService = metadataService;
    }


    @RequestMapping(method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> createTenantMetadata(@RequestBody() MetadataDto metadataDto){
        Response response =  new Response(null,null,metadataService.createMetadata(metadataDto));
        return new ResponseEntity<Response>(response, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> updateTenantMetadata(@RequestBody() MetadataDto metadataDto){
        Response response =  new Response(null,null,metadataService.updateMetadata(metadataDto));
        return new ResponseEntity<Response>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/{tenantId}",method = RequestMethod.GET)
    public ResponseEntity<Response> getTenantMetadataById(@PathVariable() String tenantId){
        Response response =  new Response(null,null,metadataService.getMetadata(tenantId));
        return new ResponseEntity<Response>(response,HttpStatus.OK);
    }

    @RequestMapping(value = "/{tenantId}",method = RequestMethod.DELETE)
    public ResponseEntity<Response> deleteTenantMetadataById(@PathVariable() String tenantId){
        Response response =  new Response(null,null,metadataService.deleteMetadata(tenantId));
        return new ResponseEntity<Response>(response,HttpStatus.OK);
    }




}
