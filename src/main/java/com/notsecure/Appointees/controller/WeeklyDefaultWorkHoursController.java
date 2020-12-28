package com.notsecure.Appointees.controller;

import com.notsecure.Appointees.entity.WeeklyDefaultWorkHours;
import com.notsecure.Appointees.service.CustomDaysService;
import com.notsecure.Appointees.service.WeeklyDefaultWorkHoursService;
import com.notsecure.Appointees.utilityservices.MonthlyBusinessWorkDaysOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
public class WeeklyDefaultWorkHoursController {

@Autowired
WeeklyDefaultWorkHoursService weeklyDefaultWorkHoursService;

@Autowired
MonthlyBusinessWorkDaysOperations monthlyBusinessWorkDaysOperations;

@Autowired
CustomDaysService customDaysService;

@RequestMapping("/company/weeklydefaultworkhours/{id}")
public ResponseEntity<Optional<WeeklyDefaultWorkHours>> getWeeklyDefaultWorkHoursService(@PathVariable Long id){

   return ResponseEntity.status(HttpStatus.OK).body(null);
}

}
