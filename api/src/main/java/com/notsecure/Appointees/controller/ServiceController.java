package com.notsecure.Appointees.controller;

import com.notsecure.Appointees.entity.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ServiceController {
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

@RequestMapping("/public/{companyId}/service/{serviceId}")
public ResponseEntity<Service> getServiceById(@PathVariable Long companyId, @PathVariable Long serviceId) {
   return null;
}

@RequestMapping(value = "/admin/{companyId}/service", method = RequestMethod.POST)
public ResponseEntity<Service> saveService(@PathVariable Long companyId, @RequestBody Service service) {
   return null;
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
