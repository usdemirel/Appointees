package com.notsecure.Appointees.controller;

import com.notsecure.Appointees.model.OutputWorkDay;
import com.notsecure.Appointees.service.WeeklyWorkHoursService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDate;
import java.util.List;

/*
* OLD Version
* Should be checked to see if there is anything useful.
* If not, can be deleted safely.
* */

@RestController
public class WorkHoursController {

Logger logger = LoggerFactory.getLogger(WorkHoursController.class);

@Autowired
WeeklyWorkHoursService weeklyWorkHoursService;

@RequestMapping("/{company}/default-work-hours/{day}")
public List<OutputWorkDay> getDefaultWorkHours(@PathVariable String company, @PathVariable int day){
   List<OutputWorkDay> defaultDays = weeklyWorkHoursService.getDefaultDays(company,day);
   
   return defaultDays;
}

@RequestMapping("/{company}/work-hours/{timeWindow}/{date}")
public List<OutputWorkDay> workHours(@PathVariable String company, @PathVariable String timeWindow, @PathVariable String date) {

//   List<OutputWorkDay> outputWorkDays = weeklyWorkHoursService.getWeeklyHours(company, timeWindow, date);
   weeklyWorkHoursService.getServiceWorkHours("a",12L,"a",LocalDate.now());
   return null;
}

///amazon/work-hours/service-id/(dayORweekORmonthORyear)/2018-05-20
@RequestMapping("/{company}/work-hours/{service-id}/{timeWindow}/{date}")
public List<OutputWorkDay> getServiceWorkHours(@PathVariable String company, @PathVariable("service-id") long serviceId, @PathVariable String timeWindow, @PathVariable LocalDate date){
   List<OutputWorkDay> serviceWorkHoursArr = weeklyWorkHoursService.getServiceWorkHours(company,serviceId,timeWindow,date);
   return null;
}

}
