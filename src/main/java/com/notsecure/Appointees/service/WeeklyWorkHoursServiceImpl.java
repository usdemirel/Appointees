package com.notsecure.Appointees.service;

import com.notsecure.Appointees.entity.DailyWorkHours;
import com.notsecure.Appointees.entity.WeeklyWorkHours;
import com.notsecure.Appointees.repository.CustomDaysRepository;
import com.notsecure.Appointees.repository.WeeklyWorkHoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class WeeklyWorkHoursServiceImpl implements WeeklyWorkHoursService {

@Autowired
WeeklyWorkHoursRepository weeklyWorkHoursRepository;
@Autowired
CustomDaysRepository customDaysRepository;

@Override
public Optional<WeeklyWorkHours> findById(Long id) {
  return weeklyWorkHoursRepository.findById(id);
}

@Override
public Optional<WeeklyWorkHours> findByCompanyName(String name) {
  return weeklyWorkHoursRepository.findByCompany_Name(name);
}

@Override
public Optional<WeeklyWorkHours> getWeeklyHours(String company, String timeWindow, String dateStr) {
 
 String[] dateProvided = dateStr.split("-");
 System.out.println(dateProvided[0] + " " + dateProvided[1] + " " + dateProvided[2]);
 LocalDate date = LocalDate.of(Integer.parseInt(dateProvided[0]),Integer.parseInt(dateProvided[1]),Integer.parseInt(dateProvided[2]));
 
  if (timeWindow.equalsIgnoreCase("day")) {
   System.out.println("Custome Day: " + customDaysRepository.findByCompany_NameAndAndServiceProviderIsNullAndDate(company,date));
   System.out.println("Default day: " + getDailyWorkHours(findByCompanyName(company).get(),date));
  
  } else if (timeWindow.equalsIgnoreCase("week")) {
  
  } else if (timeWindow.equalsIgnoreCase("month")) {
  
  } else if (timeWindow.equalsIgnoreCase("year")) {
  
  } else {
    return Optional.empty();
  }
  
  return Optional.empty();
}

private DailyWorkHours getDailyWorkHours(WeeklyWorkHours weeklyWorkHours, LocalDate date){
  DailyWorkHours dailyWorkHours = new DailyWorkHours();
  if(weeklyWorkHours != null){
    System.out.println(date.getDayOfWeek());
    switch (date.getDayOfWeek()){
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
  }else return null;
  return dailyWorkHours;
}


}
