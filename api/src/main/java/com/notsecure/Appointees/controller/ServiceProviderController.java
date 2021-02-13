package com.notsecure.Appointees.controller;

import com.notsecure.Appointees.entity.ServiceProvider;
import com.notsecure.Appointees.service.ServiceProviderService;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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
   }catch(DuplicateKeyException e){
      throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage(),e);
   }   catch(Exception e){
      throw new ResponseStatusException(HttpStatus.NOT_MODIFIED, e.getMessage(),e);
   }
}

//TODO: List All Service Providers By BranchId
@RequestMapping("/public/branch/{branchId}/serviveproviders")
public ResponseEntity<List<ServiceProvider>> getServiceProvidersByBranchId(@PathVariable Long branchId){
   try{
      return ResponseEntity.status(HttpStatus.OK).body(serviceProviderService.findAllByBranchId(branchId));
   } catch (NotFoundException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
   }
}

//TODO: List All Service Providers By ServiceId
@RequestMapping("/public/service/{serviceId}/serviveproviders")
public ResponseEntity<List<ServiceProvider>> getServiceProvidersByServiceId(@PathVariable Long serviceId){
   try{
      return ResponseEntity.status(HttpStatus.OK).body(serviceProviderService.findAllByServicesId(serviceId));
   } catch (NotFoundException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
   }
}

//TODO: List All Service Providers By BranchId And ServiceId
@RequestMapping("/public/branch/{branchId}/service/{serviceId}/serviveproviders")
public ResponseEntity<List<ServiceProvider>> getServiceProvidersByBranchId(@PathVariable Long branchId, @PathVariable Long serviceId){
   try{
      return ResponseEntity.status(HttpStatus.OK).body(serviceProviderService.findAllByBranchIdAndServicesId(branchId,serviceId));
   } catch (NotFoundException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
   }
}






}
