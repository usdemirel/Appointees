package com.notsecure.Appointees.controller;

import com.notsecure.Appointees.model.OutputWorkDay;
import com.notsecure.Appointees.service.WeeklyWorkHoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WorkHoursController {
  ///amazon/work-hours/day/2007-08-16
  @Autowired
  WeeklyWorkHoursService weeklyWorkHoursService;

@RequestMapping("/{company}/work-hours/{timeWindow}/{date}")
public List<OutputWorkDay> workHours(@PathVariable String company, @PathVariable String timeWindow, @PathVariable String date){
  
  List<OutputWorkDay> outputWorkDays= weeklyWorkHoursService.getWeeklyHours(company,timeWindow,date);

  return outputWorkDays;
}


}
