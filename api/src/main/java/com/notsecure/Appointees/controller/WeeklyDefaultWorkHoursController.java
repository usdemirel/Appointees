package com.notsecure.Appointees.controller;

import com.notsecure.Appointees.entity.WeeklyDefaultWorkHours;
import com.notsecure.Appointees.service.CustomDaysService;
import com.notsecure.Appointees.service.WeeklyDefaultWorkHoursService;
import com.notsecure.Appointees.utilityservices.MonthlyBusinessWorkDaysOperations;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class WeeklyDefaultWorkHoursController {

@Autowired
WeeklyDefaultWorkHoursService weeklyDefaultWorkHoursService;

@Autowired
MonthlyBusinessWorkDaysOperations monthlyBusinessWorkDaysOperations;

@Autowired
CustomDaysService customDaysService;

@RequestMapping("/company/weeklydefaultworkhours/{id}")
public ResponseEntity<WeeklyDefaultWorkHours> getWeeklyDefaultWorkHoursService(@PathVariable Long id){
   try{
      return ResponseEntity.status(HttpStatus.OK).body(weeklyDefaultWorkHoursService.findById(id).get());
   } catch (NotFoundException e) {
      throw  new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(),e);
   }
}

@RequestMapping("/company/weeklydefaultworkhours")
public ResponseEntity<WeeklyDefaultWorkHours> saveWeeklyDefaultWorkHoursService(@RequestBody WeeklyDefaultWorkHours weeklyDefaultWorkHours){
   WeeklyDefaultWorkHours defaultWorkHours = null;
   try {
      defaultWorkHours = weeklyDefaultWorkHoursService.save(weeklyDefaultWorkHours);
   } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.NOT_MODIFIED,e.getMessage(),e);
   }
   return ResponseEntity.status(HttpStatus.CREATED).body(defaultWorkHours);
}

}
