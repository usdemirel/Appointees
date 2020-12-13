package com.notsecure.Appointees.controller;

import com.notsecure.Appointees.entity.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClientController {
Logger logger = LoggerFactory.getLogger(ClientController.class);


@RequestMapping("/admin/{companyId}/clients}")
public ResponseEntity<Client> getAllClients(@PathVariable Long companyId) {
   return null;
}

@RequestMapping("/public/{companyId}/client/{clientId}")
public ResponseEntity<Client> getClientById(@PathVariable Long companyId, @PathVariable Long clientId) {
   return null;
}

@RequestMapping(value = "/admin/{companyId}/client", method = RequestMethod.POST)
public ResponseEntity<Client> saveClient(@PathVariable Long companyId, @RequestBody Client client) {
   return null;
}

@RequestMapping(value = "/admin/{companyId}/client", method = RequestMethod.DELETE)
public ResponseEntity<Client> deactivateClient(@PathVariable Long companyId, @RequestBody Client client) {
   return null;
}

@RequestMapping(value = "/admin/{companyId}/client", method = RequestMethod.PUT)
public ResponseEntity<Client> updateClient(@PathVariable Long companyId, @RequestBody Client client) {
   return null;
}






}
