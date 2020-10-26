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
  
  String[] dateProvided = date.split("-");
  System.out.println(dateProvided[0] + " " + dateProvided[1] + " " + dateProvided[2]);
  LocalDate day = LocalDate.of(Integer.parseInt(dateProvided[0]),Integer.parseInt(dateProvided[1]),Integer.parseInt(dateProvided[2]));
  WeeklyWorkHours weeklyWorkHours = weeklyWorkHoursService.findByCompanyName(company).get();
  DailyWorkHours dailyWorkHours = new DailyWorkHours();
  if(weeklyWorkHours != null){
    System.out.println(day.getDayOfWeek());
    switch (day.getDayOfWeek()){
      case MONDAY:
        dailyWorkHours = weeklyWorkHours.getMonday();
        break;
      case TUESDAY:
        dailyWorkHours = weeklyWorkHours.getTuesday();
        break;
      case WEDNESDAY:
        dailyWorkHours = weeklyWorkHours.getWednesday();
        break;
      case THURSDAY:
        dailyWorkHours = weeklyWorkHours.getThursday();
        break;
      case FRIDAY:
        dailyWorkHours = weeklyWorkHours.getFriday();
        break;
      case SATURDAY:
        dailyWorkHours = weeklyWorkHours.getSaturday();
        break;
      case SUNDAY:
        dailyWorkHours = weeklyWorkHours.getSunday();
        break;
      default:
        System.out.println("offf");
    }
  }

  if(timeWindow.equalsIgnoreCase("day")){
    System.out.println(dailyWorkHours.toString());
  }


  return "Arrays.toString(dateProvided)";
}


}
