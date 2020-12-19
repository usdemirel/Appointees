package com.notsecure.Appointees.utilityservices;

import com.notsecure.Appointees.entity.CustomDays;
import com.notsecure.Appointees.entity.MonthlyBusinessWorkDays;
import com.notsecure.Appointees.entity.WeeklyDefaultWorkHours;
import com.notsecure.Appointees.model.ErrorMessages;
import com.notsecure.Appointees.service.CustomDaysService;
import com.notsecure.Appointees.service.WeeklyDefaultWorkHoursService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MonthlyBusinessWorkDaysOperationsImpl implements MonthlyBusinessWorkDaysOperations {

@Autowired
WeeklyDefaultWorkHoursService weeklyDefaultWorkHoursService;

@Autowired
CustomDaysService customDaysService;
   
   /*
Date To be applied: Wednesday, 12/30/2020

SELECT * FROM WEEKLY_DEFAULT_WORK_HOURS;
ID  	EFFECTIVE_BY  	FRIDAY  	                  MONDAY  	                  SATURDAY  	   SUNDAY  	   THURSDAY  	            TUESDAY  	            WEDNESDAY  	            BRANCH_ID  	COMPANY_ID  	SERVICE_ID  	SERVICE_PROVIDER_ID
1600	2021-04-30	   08:00,12:00,13:00,18:00	   08:00,12:00,13:00,18:01	   08:00,13:00	   null	      08:00,12:00,13:00,18:04	08:00,12:00,13:00,18:05	08:00,12:00,13:00,18:06	null	      500	         null	         null
1601	2021-04-30	   08:00,16:00	               default	                  default	      default	   default	               default	               default	               300	      500	         null	         null
1602	2020-12-25	   08:00,12:00	               08:00,12:00	               null	         null	      08:00,12:00	            default	               default	               300	      500	         1000	         1100
1603	2021-04-30	   12:30,16:00                12:30,16:00	               default        default	   12:00,14:00	            null	                  null	                  300	      500	         1001	         1100

ID  	CUSTOM_DATE  	DAILY_WORK_HOURS  	DATE_CREATED  	      REASON  	                     BRANCH_ID  	COMPANY_ID  	SERVICE_PROVIDER_ID
600	2020-12-30	   08:00,13:00	         2020-12-10 11:18:00	EARLY LEAVE BEFORE NEW YEAR	300	      500	         null
601	2020-12-25	   12:00,20:00	         2020-12-10 11:18:00	CHRISTMAS SCHEDULE -Busy-	   301	      500	         null

 */

/*
Here is a sample call!
(LocalDate.of(2020,12,1),
weeklyDefaultWorkHoursService.findWeeklyDefaultWorkHoursByServiceIsNullAndServiceProviderIsNullAndCompanyIdAndEffectiveByIsAfterOrderByEffectiveBy
   (500L,LocalDate.of(2020,12,31)), //last day
customDaysService.findByCompanyIdAndServiceProviderIsNullAndCustomDateIsBetweenAndBranchIdOrBranchIsNullOrderByCustomDate
   (500L,LocalDate.of(2020,12,1),LocalDate.of(2020,12,31),300L)
 */

@Override
public String createMonthlyData(LocalDate firstDay, List<WeeklyDefaultWorkHours> weeklyDefaultWorkHours, List<CustomDays> customDays) throws NotFoundException {
   
   WeeklyDefaultWorkHours branch = null, company = null;
   for (WeeklyDefaultWorkHours temp : weeklyDefaultWorkHours) {
      if (temp.getBranch() == null && company == null) company = temp;
      else if (temp.getBranch() != null && branch == null) branch = temp;
      else if (branch != null && company != null) break;
   }
   
   if (branch == null) throw new NotFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage() + ": Please extend the Branch's Effectiveness day!");
   if (company == null) throw new NotFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage() + ": Please extend the Company's Effectiveness day!");
   
   String[] defaultDays = generateWeeklyDefaultWorkHours(branch,company);
   
   StringBuilder sb = new StringBuilder("");
   LocalDate finalDate = firstDay.plusDays(firstDay.lengthOfMonth());
   for (LocalDate day = firstDay; day.isBefore(finalDate); day = day.plusDays(1)) {
      sb.append((defaultDays[day.getDayOfWeek().getValue()].equals("closed") ? "0" : "1") + ",");
   }
   return sb.deleteCharAt(sb.lastIndexOf(",")).toString();
}

private String[] generateWeeklyDefaultWorkHours(WeeklyDefaultWorkHours branch, WeeklyDefaultWorkHours company){
   return new String[] {
                   branch.getSunday().equals("default") ? company.getSunday() : branch.getSunday(),
                   branch.getMonday().equals("default") ? company.getMonday() : branch.getMonday(),
                   branch.getTuesday().equals("default") ? company.getTuesday() : branch.getTuesday(),
                   branch.getWednesday().equals("default") ? company.getWednesday() : branch.getWednesday(),
                   branch.getThursday().equals("default") ? company.getThursday() : branch.getThursday(),
                   branch.getFriday().equals("default") ? company.getFriday() : branch.getFriday(),
                   branch.getSaturday().equals("default") ? company.getSaturday() : branch.getSaturday(),
                   branch.getSunday().equals("default") ? company.getSunday() : branch.getSunday()
   };
}

@Override
public MonthlyBusinessWorkDays updateADayInMonthlyData(MonthlyBusinessWorkDays oldMonthlyData, LocalDate dateToBeChanged, int newValue) {
   String[] daysArr = oldMonthlyData.getMonthlyData().split(",");
   daysArr[dateToBeChanged.getDayOfMonth() - 1] = String.valueOf(newValue);
   StringBuilder monthlyDataSb = new StringBuilder("");
   Arrays.asList(daysArr).stream().map(day -> day + ",").forEach(monthlyDataSb::append);
   return oldMonthlyData.setMonthlyData(monthlyDataSb.deleteCharAt(monthlyDataSb.length() - 1).toString());
}

@Override
public String retrieveADay(MonthlyBusinessWorkDays monthlyData, LocalDate date) {
   String[] daysArr = monthlyData.getMonthlyData().split(",");
   return daysArr[date.getDayOfMonth() - 1];
}

@Override
public String updateAllSingleDaysInMonthlyData(MonthlyBusinessWorkDays monthlyData, int day, int newValue) {
   int firstDay = (day - monthlyData.getFirstDayOfMonth().getDayOfWeek().getValue()+7)%7;
   String[] daysArr = monthlyData.getMonthlyData().split(",");
   while (firstDay <= monthlyData.getFirstDayOfMonth().lengthOfMonth()) {
      daysArr[firstDay] = String.valueOf(newValue);
      firstDay += 7;
   }
   System.out.println(Arrays.asList(daysArr).toString());
   return null;
}

@Override
public Map<Integer, String> createMonthlyYearDataForBranch(List<WeeklyDefaultWorkHours> weeklyDefaultWorkHours, List<CustomDays> customDays, String selection) throws NotFoundException {
   return null;
}

private String[] generateWeeklyDefaultWorkHours(WeeklyDefaultWorkHours weeklyDefaultWorkHours){
   return new String[] {
                   weeklyDefaultWorkHours.getSunday().equals("closed") ? "0" : weeklyDefaultWorkHours.getSunday().equals("default") ? "default" : "1",
                   weeklyDefaultWorkHours.getMonday().equals("closed") ? "0" : weeklyDefaultWorkHours.getMonday().equals("default") ? "default" : "1",
                   weeklyDefaultWorkHours.getTuesday().equals("closed") ? "0" : weeklyDefaultWorkHours.getTuesday().equals("default") ? "default" : "1",
                   weeklyDefaultWorkHours.getWednesday().equals("closed") ? "0" : weeklyDefaultWorkHours.getWednesday().equals("default") ? "default" : "1",
                   weeklyDefaultWorkHours.getThursday().equals("closed") ? "0" : weeklyDefaultWorkHours.getThursday().equals("default") ? "default" : "1",
                   weeklyDefaultWorkHours.getFriday().equals("closed") ? "0" : weeklyDefaultWorkHours.getFriday().equals("default") ? "default" : "1",
                   weeklyDefaultWorkHours.getSaturday().equals("closed") ? "0" : weeklyDefaultWorkHours.getSaturday().equals("default") ? "default" : "1",
                   weeklyDefaultWorkHours.getSunday().equals("closed") ? "0" : weeklyDefaultWorkHours.getSunday().equals("default") ? "default" : "1"
   };
}


/*
SELECT * FROM WEEKLY_DEFAULT_WORK_HOURS;
SELECT * FROM WEEKLY_DEFAULT_WORK_HOURS WHERE COMPANY_ID = 500 AND BRANCH_ID IS NULL OR BRANCH_ID = 300 AND SERVICE_ID IS NULL ORDER BY EFFECTIVE_BY;
SELECT * FROM WEEKLY_DEFAULT_WORK_HOURS WHERE BRANCH_ID = 300 AND SERVICE_ID IS NULL ORDER BY EFFECTIVE_BY;
 */
@Override
public Map<Integer, String> createMonthlyYearDataForBranch(Long companyId, int year, int initMonth, int endMonth) throws NotFoundException {
   /*
   DATA COLLECTION
    */
   LocalDate lastDay = LocalDate.of(year,endMonth,LocalDate.of(year,endMonth,01).lengthOfMonth());
   List<WeeklyDefaultWorkHours> companyWeeklyDefaultWorkHoursList = weeklyDefaultWorkHoursService.findWeeklyDefaultWorkHoursByEffectiveByIsAfterAndCompanyIdAndBranchIsNullOrBranchIdOrderByEffectiveBy(LocalDate.of(year,initMonth,1), companyId,null);
   List<CustomDays> customDays = customDaysService.findByCompanyIdAndServiceProviderIsNullAndCustomDateIsBetweenAndBranchIdOrBranchIsNullOrderByCustomDate(companyId,LocalDate.of(year,initMonth,1),lastDay,null);
   if(companyWeeklyDefaultWorkHoursList.size()==0) throw new NotFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
   ArrayDeque<CustomDays> customDayArrayDeque = new ArrayDeque<>(customDays.stream().filter(data -> data.getCustomDate().isAfter(LocalDate.of(year,initMonth,1).minusDays(1))).collect(Collectors.toList()));
   
   /*
   MAP INITIALIZATION
    */
   Map<Integer, StringBuilder> companyMap = new HashMap<>();
   for(int i=initMonth; i<=endMonth; i++) companyMap.put(i, new StringBuilder(""));
   
   /*
   MAPPPING
    */
   int counter = 0;
   for(WeeklyDefaultWorkHours company : companyWeeklyDefaultWorkHoursList){
      String[] companyWeeklyData = generateWeeklyDefaultWorkHours(company);
      for(LocalDate day=LocalDate.of(year,initMonth,1).plusDays(counter); day.isBefore(company.getEffectiveBy().plusDays(1)) && day.isBefore(lastDay.plusDays(1)); day=day.plusDays(1)){
         String dayValue="";
         if(!customDayArrayDeque.isEmpty() && customDayArrayDeque.getFirst().getCustomDate().equals(day)){
            dayValue = customDayArrayDeque.remove().getDailyWorkHours().equals("closed") ? "0," : "1,";
         }else dayValue = companyWeeklyData[day.getDayOfWeek().getValue()] + ",";
         companyMap.put(day.getMonthValue(), companyMap.get(day.getMonthValue()).append(dayValue));
         counter++;
      }
   }
   
   System.out.println(companyMap);
   
   return null;
}

}


//   keep it for now..
//   Object[][] days = {{"monday",1},{"tuesday",2},{"wednesday,3"},{"thursday",4},{"friday",5},{"saturday",6},{"sunday",7}};
//   Map<String, Integer> dayMappping = Stream.of(Arrays.asList(days)).collect(Collectors.toMap(data -> (String) data.get(0)[0], data -> (Integer) data.get(0)[1]));
