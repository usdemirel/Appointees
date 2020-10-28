package com.notsecure.Appointees.service;

import com.notsecure.Appointees.entity.CustomDays;
import com.notsecure.Appointees.entity.DailyWorkHours;
import com.notsecure.Appointees.entity.WeeklyWorkHours;
import com.notsecure.Appointees.model.OutputWorkDay;
import com.notsecure.Appointees.repository.CustomDaysRepository;
import com.notsecure.Appointees.repository.WeeklyWorkHoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
  LocalDate date = LocalDate.of(Integer.parseInt(dateProvided[0]), Integer.parseInt(dateProvided[1]), Integer.parseInt(dateProvided[2]));
  
  if (timeWindow.equalsIgnoreCase("day")) { //////// day ///////////
    Optional<CustomDays> theDay = customDaysRepository.findByCompany_NameAndAndServiceProviderIsNullAndDate(company, date);
    if (theDay.isPresent()) System.out.println("Custome Day: " + theDay.get());
    else System.out.println("Default day: " + getDefaultDayWorkHours(company, date));
    
  } else if (timeWindow.equalsIgnoreCase("week")) { ///////week///////////
    System.out.println(date.getDayOfWeek());
    System.out.println(date.getDayOfWeek().getValue());
    System.out.println("Let's go to sunday");
    date = date.minusDays(date.getDayOfWeek().getValue() % 7);
    System.out.println("new day is " + date.getDayOfWeek() + date.toString());
    
    List<CustomDays> customDaysList = customDaysRepository.findByCompany_NameAndAndServiceProviderIsNullAndDateIsBetweenOrderByDate(company, date, date.plusDays(6));
    if (!customDaysList.isEmpty()) System.out.println(customDaysList.toString());
    
    List<OutputWorkDay> outputWorkDayList = new ArrayList<>(7);
    DailyWorkHours[] dailyWorkHours = mapDefaultWeeklyWorkHours(company);
    
    int flag = 0;
    for (int i = 0; i < 7; i++) {
      OutputWorkDay outputWorkDay = new OutputWorkDay(date);
      if (flag < customDaysList.size() && customDaysList.get(flag).getDate().equals(date))
        outputWorkDay.setDailyWorkHours(customDaysList.get(flag++).getDailyWorkHours());
      else outputWorkDay.setDailyWorkHours(dailyWorkHours[date.getDayOfWeek().getValue()]);
      
      outputWorkDayList.add(outputWorkDay);
      date = date.plusDays(1);
    }
    System.out.println(outputWorkDayList);
    
  } else if (timeWindow.equalsIgnoreCase("month")) { //////////month//////
    date = date.withDayOfMonth(1);
    
    System.out.println("new day is " + date.getDayOfWeek() + date.toString());
  
    List<CustomDays> customDaysList = customDaysRepository.findByCompany_NameAndAndServiceProviderIsNullAndDateIsBetweenOrderByDate(company, date, date.plusDays(31));
    if (!customDaysList.isEmpty()) System.out.println(customDaysList.toString());
  
    List<OutputWorkDay> outputWorkDayList = new ArrayList<>(31);
    DailyWorkHours[] dailyWorkHours = mapDefaultWeeklyWorkHours(company);
  
    int flag = 0;
    for (int i = 0; i < 31; i++) {
      OutputWorkDay outputWorkDay = new OutputWorkDay(date);
      if (flag < customDaysList.size() && customDaysList.get(flag).getDate().equals(date))
        outputWorkDay.setDailyWorkHours(customDaysList.get(flag++).getDailyWorkHours());
      else outputWorkDay.setDailyWorkHours(dailyWorkHours[date.getDayOfWeek().getValue()]);
    
      outputWorkDayList.add(outputWorkDay);
      if(date.getMonthValue() != date.plusDays(1).getMonthValue()){
        System.out.println("I am breaking the loop now!");
        break;
      }
      date = date.plusDays(1);
    }
    System.out.println("month: "+ outputWorkDayList);
    
  
  } else if (timeWindow.equalsIgnoreCase("year")) { //////////// year ///////////////
  
  } else {
    return Optional.empty();
  }
  
  return Optional.empty();
}

private DailyWorkHours[] mapDefaultWeeklyWorkHours(String company) {
  DailyWorkHours[] dailyWorkHoursArr = new DailyWorkHours[8];
  Optional<WeeklyWorkHours> weeklyWorkHours = weeklyWorkHoursRepository.findByCompany_Name(company);
  if (weeklyWorkHours.isPresent()) {
    WeeklyWorkHours workDays = weeklyWorkHours.get();
    dailyWorkHoursArr[0] = workDays.getSunday();
    dailyWorkHoursArr[1] = workDays.getMonday();
    dailyWorkHoursArr[2] = workDays.getTuesday();
    dailyWorkHoursArr[3] = workDays.getWednesday();
    dailyWorkHoursArr[4] = workDays.getThursday();
    dailyWorkHoursArr[5] = workDays.getFriday();
    dailyWorkHoursArr[6] = workDays.getSaturday();
    dailyWorkHoursArr[7] = workDays.getSunday();
  }
  return dailyWorkHoursArr;
}

private DailyWorkHours getDefaultDayWorkHours(String company, LocalDate date) {
  DailyWorkHours[] dailyWorkHoursArr = mapDefaultWeeklyWorkHours(company);
  return dailyWorkHoursArr[date.getDayOfWeek().getValue()];
}

}
