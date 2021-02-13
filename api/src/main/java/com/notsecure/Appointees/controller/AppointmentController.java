package com.notsecure.Appointees.controller;

import com.notsecure.Appointees.entity.Appointment;
import com.notsecure.Appointees.service.AppointmentService;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
public class AppointmentController {
   Logger logger = LoggerFactory.getLogger(AppointmentController.class);
   
   @Autowired
   AppointmentService appointmentService;
   
   @RequestMapping("/appointment/{id}")
   public ResponseEntity<Appointment> findById(@PathVariable Long id){
      try {
         return ResponseEntity.status(HttpStatus.OK).body(appointmentService.findById(id).get());
      } catch (NotFoundException e) {
         throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(),e);
      }
   }
   
   @RequestMapping(value = "/appointment", method = RequestMethod.POST)
   public ResponseEntity<Appointment> save(@RequestBody Appointment appointment){
      try{
         return ResponseEntity.status(HttpStatus.CREATED).body(appointmentService.save(appointment));
      }catch (Exception e){
         throw new ResponseStatusException(HttpStatus.NOT_MODIFIED, e.getMessage(),e);
      }
   }
   
   
}
