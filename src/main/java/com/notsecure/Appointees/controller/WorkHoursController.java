package com.notsecure.Appointees.controller;

import com.notsecure.Appointees.entity.DailyWorkHours;
import com.notsecure.Appointees.entity.WeeklyWorkHours;
import com.notsecure.Appointees.service.WeeklyWorkHoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;

@RestController
public class WorkHoursController {
  ///amazon/work-hours/day/2007-08-16
  @Autowired
  WeeklyWorkHoursService weeklyWorkHoursService;

@RequestMapping("/{company}/work-hours/{timeWindow}/{date}")
public String workHours(@PathVariable String company, @PathVariable String timeWindow, @PathVariable String date){
  
  weeklyWorkHoursService.getWeeklyHours(company,timeWindow,date);

  return "Arrays.toString(dateProvided)";
}


}
