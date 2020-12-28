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
   public ResponseEntity<List<Client>> getAllClients(@PathVariable Long companyId) throws NotFoundException {
      List<Client> clientList = clientService.findClientsByCompany(companyId);
      if (clientList.size() == 0) throw new NotFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
      return ResponseEntity.status(HttpStatus.OK).body(clientList);
   }

   @GetMapping("/public/{companyId}/client/{clientId}")
   public ResponseEntity<Client> getClientById(@PathVariable Long companyId, @PathVariable Long clientId) throws NotFoundException {
      Optional<Company> company = companyService.findCompanyById(companyId);
      Optional<Client> client = clientService.findById(clientId);

      if (!company.isPresent() && !client.isPresent())
         throw new NotFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
      return ResponseEntity.status(HttpStatus.OK).body(client.get());
   }

   @PostMapping(value = "/admin/{companyId}/client")
   public ResponseEntity<Client> saveClient(@PathVariable Long companyId, @RequestBody Client client) throws Exception {
      try {
         logger.info("New client {} creation attempt for company {}", client.toString(), companyId);
         return ResponseEntity.status(HttpStatus.CREATED).body(clientService.save(client));
      } catch (Exception e) {
         throw new Exception(ErrorMessages.COULD_NOT_SAVE_RECORD.getErrorMessage());
      }
   }

   @DeleteMapping(value = "/admin/{companyId}/client/{clientId}")
   public ResponseEntity<Client> deactivateClient(@PathVariable Long companyId, @PathVariable Long clientId) throws Exception {
      //TODO Change add active flag to to client and
      try {
         clientService.deactivateById(clientId);
         return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
      } catch (Exception e) {
         throw new Exception(ErrorMessages.COULD_NOT_DELETE_RECORD.getErrorMessage());
      }
   }

   @PutMapping(value = "/admin/{companyId}/client/{clientId}")
   public ResponseEntity<Client> updateClient(@PathVariable Long companyId, @PathVariable Long clientId, @RequestBody Client client) throws Exception {
      try {
         if (clientService.existsById(clientId)) clientService.save(client);
         return ResponseEntity.status(HttpStatus.ACCEPTED).build();
      } catch (Exception e) {
         throw new Exception(ErrorMessages.COULD_NOT_UPDATE_RECORD.getErrorMessage());
      }
   }

   /*Add active flag
   *Allow list for company / filter
   *comment
   *guvens
   * second one
   * */

}
