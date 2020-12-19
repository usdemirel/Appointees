package com.notsecure.Appointees.controller;

import com.notsecure.Appointees.entity.MonthlyBusinessWorkDays;
import com.notsecure.Appointees.service.CustomDaysService;
import com.notsecure.Appointees.service.MonthlyBusinessWorkDaysService;
import com.notsecure.Appointees.service.WeeklyDefaultWorkHoursService;
import com.notsecure.Appointees.utilityservices.MonthlyBusinessWorkDaysOperations;
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
   @Autowired
MonthlyBusinessWorkDaysService monthlyBusinessWorkDaysService;

@RequestMapping("/getmonthlydata")
public ResponseEntity<String> getMonthlyData() throws NotFoundException {
   System.out.println("getmonthlydata");
   String montlyData = monthlyBusinessWorkDaysOperations.createMonthlyData(LocalDate.of(2020,12,1),
                   weeklyDefaultWorkHoursService.findWeeklyDefaultWorkHoursByServiceIsNullAndServiceProviderIsNullAndCompanyIdAndEffectiveByIsAfterOrderByEffectiveBy
                                   (500L,LocalDate.of(2020,12,1)), //first day
                   customDaysService.findByCompanyIdAndServiceProviderIsNullAndCustomDateIsBetweenAndBranchIdOrBranchIsNullOrderByCustomDate
                                   (500L,LocalDate.of(2020,12,1),LocalDate.of(2020,12,31),300L));
   
   weeklyDefaultWorkHoursService.findWeeklyDefaultWorkHoursByServiceIsNullAndServiceProviderIsNullAndCompanyIdAndEffectiveByIsAfterOrderByEffectiveBy
                   (500L,LocalDate.of(2020,12,1)).forEach(data -> System.out.println(data.getId()));
   
   return ResponseEntity.status(HttpStatus.OK).body(montlyData);
}

@RequestMapping("/updateadayinmonthlydata")
public ResponseEntity<MonthlyBusinessWorkDays> updateMonthlyData() throws NotFoundException {
   System.out.println("updatemonthlydata");
   MonthlyBusinessWorkDays monthlyData = monthlyBusinessWorkDaysOperations.updateADayInMonthlyData(monthlyBusinessWorkDaysService
                   .findMonthlyBusinessWorkDaysByBranchIdAndFirstDayOfMonth(300L,LocalDate.of(2020,12,01)).get(),
                   LocalDate.of(2020,12,2),3);
   
   System.out.println("++ " + monthlyBusinessWorkDaysService
                   .findMonthlyBusinessWorkDaysByBranchIdAndFirstDayOfMonth(300L,LocalDate.of(2020,12,01)).get());
   
   System.out.println(monthlyData.getCompany().getAccountInfo());
   
   //update all single weekly days..
   monthlyBusinessWorkDaysOperations.updateAllSingleDaysInMonthlyData(monthlyBusinessWorkDaysService
                   .findMonthlyBusinessWorkDaysByBranchIdAndFirstDayOfMonth(300L,LocalDate.of(2020,12,01)).get(),1,9);
   
   return ResponseEntity.status(HttpStatus.OK).body(monthlyData);
}

}
