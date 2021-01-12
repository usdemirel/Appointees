package com.notsecure.Appointees.controller;

import com.notsecure.Appointees.entity.ServiceProvider;
import com.notsecure.Appointees.service.ServiceProviderService;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

//Guven
@RestController
public class ServiceProviderController {
   @Autowired
ServiceProviderService serviceProviderService;
Logger logger = LoggerFactory.getLogger(ServiceProviderController.class);

@RequestMapping("/public/serviceprovider/{id}")
public ResponseEntity<ServiceProvider> getServiceProviderById(@PathVariable Long id){
   try{
      return ResponseEntity.status(HttpStatus.OK).body(serviceProviderService.findServiceProviderById(id).get());
   } catch (NotFoundException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
   }
}

@RequestMapping(value = "/admin/serviceprovider", method = RequestMethod.POST)
public ResponseEntity<ServiceProvider> saveServiceProvider(@RequestBody ServiceProvider serviceProvider){
   try{
      return ResponseEntity.status(HttpStatus.CREATED).body(serviceProviderService.saveServiceProvider(serviceProvider));
   }catch(Exception e){
      throw new ResponseStatusException(HttpStatus.NOT_MODIFIED, e.getMessage(),e);
   }
}

}
