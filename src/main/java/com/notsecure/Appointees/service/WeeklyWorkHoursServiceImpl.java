package com.notsecure.Appointees.service;

import com.notsecure.Appointees.entity.CustomDays;
import com.notsecure.Appointees.entity.DailyWorkHours;
import com.notsecure.Appointees.entity.WeeklyWorkHours;
import com.notsecure.Appointees.model.OutputWorkDay;
import com.notsecure.Appointees.repository.CustomDaysRepository;
import com.notsecure.Appointees.repository.WeeklyWorkHoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.DayOfWeek;
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
   return weeklyWorkHoursRepository.findWeeklyWorkHoursByCompany_NameAndServiceIsNullAndServiceProviderIsNull(name);
}

@Override
public List<OutputWorkDay> getWeeklyHours(String company, String timeWindow, String dateStr) {
   
   String[] dateProvided = dateStr.split("-");
   LocalDate date = LocalDate.of(Integer.parseInt(dateProvided[0]), Integer.parseInt(dateProvided[1]), Integer.parseInt(dateProvided[2]));
   DailyWorkHours[] dailyWorkHoursArr = mapDefaultWeeklyWorkHours(company);
   List<OutputWorkDay> outputWorkDayList = null;
   
   if (timeWindow.equalsIgnoreCase("day")) { //////// day ///////////
      Optional<CustomDays> theDay = customDaysRepository.findByCompany_NameAndAndServiceProviderIsNullAndDate(company, date);
      if (theDay.isPresent()) System.out.println("Custom Day: " + theDay.get());
      else System.out.println("Default day: " + dailyWorkHoursArr[date.getDayOfWeek().getValue()]);
      new ArrayList<>(1);
      
   } else if (timeWindow.equalsIgnoreCase("week")) {
      outputWorkDayList = getDays(dailyWorkHoursArr, company, date.minusDays(date.getDayOfWeek().getValue() % 7), timeWindow);
   } else if (timeWindow.equalsIgnoreCase("month")) {
      outputWorkDayList = getDays(dailyWorkHoursArr, company, date.withDayOfMonth(1), timeWindow);
   } else if (timeWindow.equalsIgnoreCase("year")) {
      outputWorkDayList = getDays(dailyWorkHoursArr, company, date.withDayOfYear(1), timeWindow);
   }
   System.out.println(outputWorkDayList);
   return outputWorkDayList;
}

public List<OutputWorkDay> getDefaultDays(String company, int day){
   DailyWorkHours[] dailyWorkHoursArr = mapDefaultWeeklyWorkHours(company);
   List<OutputWorkDay> defaultDays = new ArrayList<>();
   if(day != 7) defaultDays.add(new OutputWorkDay(null,dailyWorkHoursArr[day],DayOfWeek.of(day == 0 ? 7 : day).name(),day));
   else{
      for(int i=1; i<8; i++) defaultDays.add(new OutputWorkDay(null,dailyWorkHoursArr[i], DayOfWeek.of(i).name(),i%7));
   }
   return defaultDays;
}

@Override
public List<OutputWorkDay> getServiceWorkHours(String company, long serviceId, String timeWindow, LocalDate date) {
   ///amazon/work-hours/service-id/(dayORweekORmonthORyear)/2018-05-20
   
   
   
   return null;
}

private List<OutputWorkDay> getDays(DailyWorkHours[] dailyWorkHoursArr, String company, LocalDate date, String timeWindow) {
   int flag = 0;
   int plusDay = timeWindow.equals("day") ? 0 : timeWindow.equals("week") ? 7 : timeWindow.equals("month") ? 31 : date.isLeapYear() ? 366 : 365;
   List<CustomDays> customDaysList = customDaysRepository.findByCompany_NameAndAndServiceProviderIsNullAndDateIsBetweenOrderByDate(company, date, date.plusDays(plusDay));
   List<OutputWorkDay> outputWorkDayList = new ArrayList<>(plusDay);
   
   for (int i = 0; i < plusDay; i++) {
      OutputWorkDay outputWorkDay = new OutputWorkDay(date);
      if (flag < customDaysList.size() && customDaysList.get(flag).getDate().equals(date))
         outputWorkDay.setDailyWorkHours(customDaysList.get(flag++).getDailyWorkHours());
      else outputWorkDay.setDailyWorkHours(dailyWorkHoursArr[date.getDayOfWeek().getValue()]);
      outputWorkDayList.add(outputWorkDay);
      if (timeWindow.equals("month") && date.getMonthValue() != date.plusDays(1).getMonthValue()) break;
      else date = date.plusDays(1);
   }
   
   return outputWorkDayList;
}

private DailyWorkHours[] mapDefaultWeeklyWorkHours(String company) {
   DailyWorkHours[] dailyWorkHoursArr = new DailyWorkHours[8];
   Optional<WeeklyWorkHours> weeklyWorkHours = weeklyWorkHoursRepository.findWeeklyWorkHoursByCompany_NameAndServiceIsNullAndServiceProviderIsNull(company);
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

}
