package com.notsecure.Appointees.controller;

import com.notsecure.Appointees.entity.WeeklyWorkHours;
import com.notsecure.Appointees.service.WeeklyWorkHoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Welcome {
 
 @Autowired
WeeklyWorkHoursService weeklyWorkHoursService;
 
 @RequestMapping("/welcome")
public String welcome(){
  System.out.println(weeklyWorkHoursService.findById(301L).get().getSaturday());
  return "welcome";
 }
 
 
}
