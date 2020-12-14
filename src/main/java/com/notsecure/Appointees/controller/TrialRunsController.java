package com.notsecure.Appointees.controller;

import com.notsecure.Appointees.service.CustomDaysService;
import com.notsecure.Appointees.service.CustomDaysServiceImpl;
import com.notsecure.Appointees.service.WeeklyDefaultWorkHoursService;
import com.notsecure.Appointees.utilityservices.MonthlyBusinessWorkDaysOperations;
import com.notsecure.Appointees.utilityservices.MonthlyBusinessWorkDaysOperationsImpl;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class TrialRunsController {
   @Autowired
CustomDaysService customDaysService;
   @Autowired
MonthlyBusinessWorkDaysOperations monthlyBusinessWorkDaysOperations;
   @Autowired
WeeklyDefaultWorkHoursService weeklyDefaultWorkHoursService;

@RequestMapping("/getmonthlydata")
public ResponseEntity<String> getMonthlyData() throws NotFoundException {
   System.out.println("getmonthlydata");
   String montlyData = monthlyBusinessWorkDaysOperations.createMonthlyData(LocalDate.of(2020,12,1),
                   weeklyDefaultWorkHoursService.findWeeklyDefaultWorkHoursByServiceIsNullAndServiceProviderIsNullAndCompanyIdAndEffectiveByIsAfterOrderByEffectiveBy
                                   (500L,LocalDate.of(2020,12,31)), //last day
                   customDaysService.findByCompanyIdAndServiceProviderIsNullAndCustomDateIsBetweenAndBranchIdOrBranchIsNullOrderByCustomDate
                                   (500L,LocalDate.of(2020,12,1),LocalDate.of(2020,12,31),300L));
   
   return ResponseEntity.status(HttpStatus.OK).body(montlyData);
}


}
