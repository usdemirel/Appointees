package com.notsecure.Appointees.utilityservices;

import com.notsecure.Appointees.entity.CustomDays;
import com.notsecure.Appointees.entity.WeeklyDefaultWorkHours;
import com.notsecure.Appointees.model.ErrorMessages;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.*;

@Service
public class MonthlyBusinessWorkDaysOperationsImpl implements MonthlyBusinessWorkDaysOperations{
   
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
   for(WeeklyDefaultWorkHours temp : weeklyDefaultWorkHours) {
      if(temp.getBranch() == null && company==null) company = temp;
      else if(temp.getBranch() != null && branch == null) branch = temp;
      else if(branch != null && company != null) break;
   }
   
   if(branch==null) throw new NotFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage() + ": Please extend the Branch's Effectiveness day!");
   if(company==null) throw new NotFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage() + ": Please extend the Company's Effectiveness day!");
   
   String[] defaultDays = {
                   branch.getSunday().equals("default") ? company.getSunday() : branch.getSunday(),
                   branch.getMonday().equals("default") ? company.getMonday() : branch.getMonday(),
                   branch.getTuesday().equals("default") ? company.getTuesday() : branch.getTuesday(),
                   branch.getWednesday().equals("default") ? company.getWednesday() : branch.getWednesday(),
                   branch.getThursday().equals("default") ? company.getThursday() : branch.getThursday(),
                   branch.getFriday().equals("default") ? company.getFriday() : branch.getFriday(),
                   branch.getSaturday().equals("default") ? company.getSaturday() : branch.getSaturday(),
                   branch.getSunday().equals("default") ? company.getSunday() : branch.getSunday()
   };
   
   StringBuilder sb = new StringBuilder("");
   LocalDate finalDate = firstDay.plusDays(firstDay.lengthOfMonth());
   for(LocalDate day = firstDay;  day.isBefore(finalDate); day=day.plusDays(1)){
      sb.append((defaultDays[day.getDayOfWeek().getValue()].equals("closed") ? "0" : "1") + ",");
   }
   return sb.deleteCharAt(sb.lastIndexOf(",")).toString();
}

@Override
public String updateADayInMonthlyData(String oldMonthlyData, LocalDate dateToBeChanged, int newValue) {
   return null;
}

@Override
public int retrieveADay(LocalDate date) {
   return 0;
}

@Override
public String updateAllSingleDaysInMonthlyData(LocalDate firstDayOfMonth, int day, int newValue) {
   return null;
}

}