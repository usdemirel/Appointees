package com.notsecure.Appointees.controller;

import com.notsecure.Appointees.entity.Service;
import com.notsecure.Appointees.model.ErrorMessages;
import com.notsecure.Appointees.service.ServiceService;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class ServiceController {
   
   @Autowired
ServiceService serviceService;
Logger logger = LoggerFactory.getLogger(ServiceController.class);


@RequestMapping("/admin/{companyId}/services/deactive")
public ResponseEntity<Service> getDeactivateServices(@PathVariable Long companyId) {
   return null;
}

@RequestMapping("/public/{companyId}/services/active}")
public ResponseEntity<Service> getActiveServices(@PathVariable Long companyId) {
   return null;
}

@RequestMapping("/admin/{companyId}/services/all}")
public ResponseEntity<Service> getAllServices(@PathVariable Long companyId) {
   return null;
}

@RequestMapping("/public/service/{serviceId}")
public ResponseEntity<Service> getServiceById(@PathVariable Long serviceId) {
   try {
      return ResponseEntity.status(HttpStatus.OK).body(serviceService.findServiceById(serviceId).get());
   } catch (NotFoundException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage(),e);
   }
}

@RequestMapping(value = "/admin/service", method = RequestMethod.POST)
public ResponseEntity<Service> saveService(@RequestBody Service service) {
   try {
      return ResponseEntity.status(HttpStatus.CREATED).body(serviceService.saveService(service));
   } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.NOT_MODIFIED,e.getMessage(),e);
   }
}

@RequestMapping(value = "/admin/{companyId}/service", method = RequestMethod.DELETE)
public ResponseEntity<Service> deactivateService(@PathVariable Long companyId, @RequestBody Service service) {
   return null;
}

@RequestMapping(value = "/admin/{companyId}/service", method = RequestMethod.PUT)
public ResponseEntity<Service> updateService(@PathVariable Long companyId, @RequestBody Service service) {
   return null;
}


}
