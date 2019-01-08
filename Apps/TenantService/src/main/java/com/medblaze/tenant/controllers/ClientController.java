package com.medblaze.tenant.controllers;


import com.medblaze.appscommon.interceptor.RequestContextProvider;
import com.medblaze.beans.model.ClientDto;
import com.medblaze.beans.response.Response;
import com.medblaze.dbservice.ClientService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/clients", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClientController {

    private static final Logger logger = Logger.getLogger(UserController.class);

    private final ClientService clientService;
    private final BCryptPasswordEncoder encoder;

    @Autowired
    public ClientController(ClientService clientService,BCryptPasswordEncoder encoder) {
        this.clientService = clientService;
        this.encoder = encoder;
    }

    @RequestMapping(method= RequestMethod.GET)
    public ResponseEntity<Response> getAllClients() {
        Response response =  new Response(null,null,clientService.getAllClients());
        return new ResponseEntity<Response>(response, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> createClient(@RequestBody() ClientDto clientDto){
        clientDto.setClientSecret(encoder.encode(clientDto.getClientSecret()));
        Response response =  new Response(null,null,clientService.createClient(clientDto));
        return new ResponseEntity<Response>(response,HttpStatus.OK);
    }

    @RequestMapping(value = "/{clientId}",method = RequestMethod.GET)
    public ResponseEntity<Response> getClientById(@PathVariable() String clientId){
        Response response =  new Response(null,null,clientService.getClientById(clientId));
        return new ResponseEntity<Response>(response,HttpStatus.OK);
    }


}
