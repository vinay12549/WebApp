package com.medblaze.tenant.controllers;

import com.medblaze.beans.model.TenantDto;
import com.medblaze.beans.response.Response;
import com.medblaze.dbservice.TenantService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping(value = "/tenants", produces = MediaType.APPLICATION_JSON_VALUE)
public class TenantController {

	private static final Logger logger = Logger.getLogger(TenantController.class);

	private final TenantService tenantService;

	@Autowired
    public TenantController(TenantService tenantService) {
        this.tenantService = tenantService;
    }

    @RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Response> getAllTenants() {
        Response response =  new Response(null,null,tenantService.getAllTenant());
        return new ResponseEntity<Response>(response,HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> createTenant(@RequestBody() TenantDto tenantDto){
        Response response =  new Response(null,null,tenantService.createTenant(tenantDto));
        return new ResponseEntity<Response>(response,HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> updateTenant(@RequestBody() TenantDto tenantDto){
        Response response =  new Response(null,null,tenantService.updateTenant(tenantDto));
        return new ResponseEntity<Response>(response,HttpStatus.OK);
    }

    @RequestMapping(value= "/init/{tenantId}" ,method = RequestMethod.POST)
    public ResponseEntity<Response> initTenant(@PathVariable() String tenantId) throws Exception {
        Response response =  new Response(null,null,tenantService.initTenant(tenantId));
        return new ResponseEntity<Response>(response,HttpStatus.OK);
    }

    @RequestMapping(value = "/{tenantId}",method = RequestMethod.GET)
    public ResponseEntity<Response> getTenantById(@PathVariable() String tenantId){
        Response response =  new Response(null,null,tenantService.getTenant(tenantId));
        return new ResponseEntity<Response>(response,HttpStatus.OK);
    }

    @RequestMapping(value = "/{tenantId}",method = RequestMethod.DELETE)
    public ResponseEntity<Response> deleteTenant(@PathVariable() String tenantId){
        Response response =  new Response(null,null,tenantService.deleteTenant(tenantId));
        return new ResponseEntity<Response>(response,HttpStatus.OK);
    }

    @RequestMapping(value = "/{tenantId}",method = RequestMethod.PUT)
    public ResponseEntity<Response> disableTenant(@PathVariable() String tenantId){
        Response response =  new Response(null,null,tenantService.disableTenant(tenantId));
        return new ResponseEntity<Response>(response,HttpStatus.OK);
    }

}
