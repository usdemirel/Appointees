package com.notsecure.Appointees.controller;

import com.notsecure.Appointees.entity.Branch;
import com.notsecure.Appointees.entity.WeeklyDefaultWorkHours;
import com.notsecure.Appointees.model.ErrorMessages;
import com.notsecure.Appointees.service.CustomDaysService;
import com.notsecure.Appointees.service.WeeklyDefaultWorkHoursService;
import com.notsecure.Appointees.utilityservices.MonthlyBusinessWorkDaysOperations;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
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
   return ResponseEntity.status(HttpStatus.OK).body(weeklyDefaultWorkHoursService.findById(id));
}

@RequestMapping(value = "/{companyId}/weeklydefaultworkhours", method = RequestMethod.POST)
public ResponseEntity<WeeklyDefaultWorkHours> saveWeeklyDefaultWorkHours(@PathVariable Long companyId, @RequestBody WeeklyDefaultWorkHours weeklyDefaultWorkHours) throws NotFoundException {
   if(weeklyDefaultWorkHours.getId() == null){
      WeeklyDefaultWorkHours saved =weeklyDefaultWorkHoursService.save(weeklyDefaultWorkHours);
      
//      monthlyBusinessWorkDaysOperations.createMonthlyYearDataForBranch(
//                      weeklyDefaultWorkHoursService.findWeeklyDefaultWorkHoursByEffectiveByIsAfterAndCompanyIdAndBranchIsNullOrBranchIdOrderByEffectiveBy(
//                                      LocalDate.of(2020,1,1), 500L,300L),
//                      customDaysService.findByCompanyIdAndServiceProviderIsNullAndCustomDateIsBetweenAndBranchIdOrBranchIsNullOrderByCustomDate(
//                                      500L,LocalDate.of(2020,1,1),LocalDate.of(2020,12,31),300L),
//                                      "branch"
//      );
   
//      monthlyBusinessWorkDaysOperations.createMonthlyYearDataForBranch(
//                      weeklyDefaultWorkHoursService.findWeeklyDefaultWorkHoursByEffectiveByIsAfterAndCompanyIdAndBranchIsNullOrBranchIdOrderByEffectiveBy(
//                                      LocalDate.of(LocalDate.now().getYear(),1,1), saved.getCompany().getId(),null), //saved.getBranch().getId()
//                      customDaysService.findByCompanyIdAndServiceProviderIsNullAndCustomDateIsBetweenAndBranchIdOrBranchIsNullOrderByCustomDate(
//                                      saved.getCompany().getId(),LocalDate.of(LocalDate.now().getYear(),1,1),LocalDate.of(LocalDate.now().getYear(),12,31),null),
//                      "branch"
//      );
   
      monthlyBusinessWorkDaysOperations.createMonthlyYearDataForBranch(500L,2020,1,12);
      
   }
   return null;
}


}
