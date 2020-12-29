package com.notsecure.Appointees.controller;

import com.notsecure.Appointees.entity.Client;
import com.notsecure.Appointees.entity.Company;
import com.notsecure.Appointees.model.ErrorMessages;
import javassist.NotFoundException;
import com.notsecure.Appointees.service.ClientService;
import com.notsecure.Appointees.service.CompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
public class ClientController {
   Logger logger = LoggerFactory.getLogger(ClientController.class);

   @Autowired
   CompanyService companyService;

   @Autowired
   ClientService clientService;

   @GetMapping("/admin/{companyId}/clients}")
   public ResponseEntity<List<Client>> getAllClients(@PathVariable Long companyId){
      try {
         return ResponseEntity.status(HttpStatus.OK).body(clientService.findClientsByCompany(companyId));
      } catch (NotFoundException e) {
         throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
      }
   }

   @GetMapping("/public/{companyId}/client/{clientId}")
   public ResponseEntity<Client> getClientById(@PathVariable Long companyId, @PathVariable Long clientId)  {
      try {
         Optional<Company> company = companyService.findCompanyById(companyId);
         Optional<Client> client = clientService.findById(clientId);
         if (!company.isPresent()){
            throw new NotFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
         }
         return ResponseEntity.status(HttpStatus.OK).body(client.get());
      } catch (NotFoundException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
      }
   }

   @PostMapping(value = "/admin/{companyId}/client")
   public ResponseEntity<Client> saveClient(@PathVariable Long companyId, @RequestBody Client client) {
      try{
         return ResponseEntity.status(HttpStatus.CREATED).body(clientService.save(client));
      }catch(Exception e){
         logger.error("New company creation attempt: {} ",client.toString());
         throw new ResponseStatusException(HttpStatus.NOT_MODIFIED,e.getMessage(),e);
      }
   }

   @DeleteMapping(value = "/admin/client/{clientId}")
   public ResponseEntity<Client> deactivateClient(@PathVariable Long clientId) {
      //TODO Change add active flag to to client and
      try {
         clientService.deactivateById(clientId);
         return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
      } catch (Exception e) {
         throw new ResponseStatusException(HttpStatus.NOT_MODIFIED,e.getMessage(),e);
      }
   }

   @PutMapping(value = "/admin/client/{clientId}")
   public ResponseEntity<Client> updateClient(@PathVariable Long clientId, @RequestBody Client client) {
      try {
         if (clientService.existsById(clientId)) clientService.save(client);
         return ResponseEntity.status(HttpStatus.ACCEPTED).build();
      } catch (Exception e) {
         throw new ResponseStatusException(HttpStatus.NOT_MODIFIED,e.getMessage(),e);
      }
   }

   /*Add active flag
   *Allow list for company / filter
   *comment
   *guvens
   * second one
   * */

}
