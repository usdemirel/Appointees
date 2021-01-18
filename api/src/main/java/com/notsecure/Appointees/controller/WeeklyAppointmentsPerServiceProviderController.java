package com.notsecure.Appointees.controller;

import com.notsecure.Appointees.entity.WeeklyAppointmentsPerServiceProvider;
import com.notsecure.Appointees.entity.WeeklyCustomServiceProviderSchedule;
import com.notsecure.Appointees.service.WeeklyAppointmentsPerServiceProviderService;
import com.notsecure.Appointees.utilityservices.WeeklyAppointmentsPerServiceProviderTextOperations;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

/*
As a second though, There's no need for this controller
 */

@RestController
public class WeeklyAppointmentsPerServiceProviderController {
   
   @Autowired
   WeeklyAppointmentsPerServiceProviderTextOperations weeklyAppointmentsPerServiceProviderTextOperations;
   
   @Autowired
   WeeklyAppointmentsPerServiceProviderService weeklyAppointmentsPerServiceProviderService;
   
   @RequestMapping("/public/weeklyserviceproviderschedule/{id}")
   public ResponseEntity<WeeklyAppointmentsPerServiceProvider> findById(@PathVariable Long id){
      try{
         return ResponseEntity.status(HttpStatus.OK).body(weeklyAppointmentsPerServiceProviderService.findWeeklyAppointmentsPerServiceProviderById(id).get());
      } catch (NotFoundException e) {
         throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage(),e);
      }
   }
   
   
   
   
   /*
   Temporary Method
   Will be deleted later..
    */
   @RequestMapping("/abc")
   public String run(){
   
      WeeklyCustomServiceProviderSchedule weeklyCustomServiceProviderSchedule =
                      new WeeklyCustomServiceProviderSchedule(2500L,null,null,null,null, LocalDate.parse("2021-01-17"),30,
                                      "07:00,07:40,08:20,09:00,09:40,10:20,11:00","12:30,13:00,13:30,14:00,14:30,15:00,15:30",
                                      "12:59,13:29,13:59,14:29,14:59,15:29","12:00,12:30,13:00,13:30,14:00,14:30,15:00,15:30,16:00,16:30,17:00,17:30,18:00,18:30",
                                      "08:00,08:30,09:00,09:30,10:00,10:30,11:00,11:30,13:00,13:30,14:00,14:30,15:00,15:30,16:00,16:30,17:00,17:30","",
                                      "08:00,08:30,09:00,09:30,10:00,10:30,11:00,11:30");
      try {
         weeklyAppointmentsPerServiceProviderTextOperations.convertWeeklyScheduleToProviderAppointments(weeklyCustomServiceProviderSchedule);
      } catch (NotFoundException e) {
         e.printStackTrace();
      }
      return null;
   }
}
