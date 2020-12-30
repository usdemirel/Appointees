package com.notsecure.Appointees.utilityservices;

import com.notsecure.Appointees.entity.*;
import com.notsecure.Appointees.service.MonthlyBusinessWorkDaysService;
import com.notsecure.Appointees.service.WeeklyDefaultWorkHoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class TextOperationsImpl implements TextOperations {

@Autowired
WeeklyDefaultWorkHoursService weeklyDefaultWorkHoursService;

@Autowired
MonthlyBusinessWorkDaysService monthlyBusinessWorkDaysService;

@Override
public WeeklyAppointmentsPerServiceProvider generateWeeklySchedule(LocalDate firstDay, List<WeeklyDefaultWorkHours> weeklyDefaultWorkHours, List<CustomDays> customDays) {
   return null;
}

@Override
public WeeklyAppointmentsPerServiceProvider generateWeeklyAppointmentsPerServiceProvider(LocalDate firstDay, Long serviceProviderId, Long companyId) {
   return null;
}

/*
	   Sunday-Dec27	 Monday-Dec28	Tuesday-Dec29	Wednesday-Dec30   Thursday-Dec31             Friday-Jan1	               Saturday-Jan2
1000	closed	       08:00,12:00   closed	      closed	         closed	                  closed	                  closed
1001	12:00,15:00	    12:30,16:00	12:59,16:00	   12:00,19:00	      08:00,12:00,13:00,18:00	   19:00,21:00	               08:00,12:00
1002	closed	       closed        07:00,12:00	   07:00,12:00	      19:00,22:00	               07:07,12:07,13:00,18:00	   13:00,22:00

700	12/1/2020	1,1,1,1,0,0,1,1,1,1,1,0,0,1,1,1,1,1,0,0,1,1,1,1,1,0,0,1,1,1,0
701	1/1/2020	   0,1,1,1,1,0,1,1,1,1,1,1,0,1,1,1,1,1,1,0,1,1,1,1,1,1,0,1,1,1,1
	
	   Sunday-Dec27	 Monday-Dec28	Tuesday-Dec29	Wednesday-Dec30   Thursday-Dec31    Friday-Jan1    Saturday-Jan2
1000	closed	       08:00,12:00	closed	      closed	         closed	         closed	      closed
1001	closed   	    12:30,16:00	12:59,16:00	   12:00,19:00	      closed	         closed	      08:00,12:00
1002	closed	       closed	      07:00,12:00	   07:00,12:00	      closed	         closed	      13:00,22:00

 */
/*
SELECT * FROM WEEKLY_CUSTOM_SERVICE_PROVIDER_SCHEDULE ;
SELECT * FROM WEEKLY_DEFAULT_WORK_HOURS WHERE SERVICE_PROVIDER_ID=1100  AND (EFFECTIVE_BY = '2020-12-27' OR EFFECTIVE_BY > '2020-12-27') ORDER BY EFFECTIVE_BY;
SELECT * FROM MONTHLY_BUSINESS_WORK_DAYS WHERE BRANCH_ID=300 AND (FIRST_DAY_OF_MONTH = '2020-12-01' OR  FIRST_DAY_OF_MONTH = '2021-01-01') ORDER BY FIRST_DAY_OF_MONTH;
SELECT * FROM SERVICE WHERE ID=1000 OR ID=1001 OR ID=1002;
*/
@Override
public List<WeeklyCustomServiceProviderSchedule> generateWeeklyCustomServiceProviderSchedule(LocalDate firstDay, Long serviceProviderId, Long companyId, Long branchId, int numOfWeeks) {
   List<WeeklyCustomServiceProviderSchedule> result = new ArrayList<>();
   List<WeeklyDefaultWorkHours> weeklyDefaultWorkHoursList = weeklyDefaultWorkHoursService.findWeeklyDefaultWorkHoursByServiceProviderIdAndCompanyIdAndEffectiveByIsAfterOrderByEffectiveBy(serviceProviderId, companyId, firstDay);
//   weeklyDefaultWorkHoursList.forEach(data -> System.out.println(data));
   
   List<MonthlyBusinessWorkDays> monthlyBusinessWorkDays = new ArrayList<>();
   if (firstDay.lengthOfMonth() - firstDay.getDayOfMonth() < numOfWeeks * 7 - 1) {
      int numOfDaysLeft = numOfWeeks * 7 - (firstDay.lengthOfMonth() - firstDay.getDayOfMonth() + 1);
      int numOfAdditionalMonths = (int) Math.ceil((double) numOfDaysLeft / (7 * 4));
      LocalDate endMonth = firstDay.plusMonths(numOfAdditionalMonths);
      System.out.println("endMonth: " + endMonth);
      monthlyBusinessWorkDays = monthlyBusinessWorkDaysService.findMonthlyBusinessWorkDaysByBranchIdAndFirstDayOfMonthIsBetweenOrderByFirstDayOfMonth(branchId, firstDay, endMonth);
   } else {
      monthlyBusinessWorkDays.add(monthlyBusinessWorkDaysService.findMonthlyBusinessWorkDaysByBranchIdAndFirstDayOfMonth(branchId, LocalDate.of(firstDay.getYear(), firstDay.getMonthValue(), 1)).get());
   }
   
   System.out.println(monthlyBusinessWorkDays);
   
   
   if (monthlyBusinessWorkDays.size() == 1 && monthlyBusinessWorkDays.get(0) == null)
      throw new NoSuchElementException("Monthly data should be generated.. Don't throw.. generate the data.."); // later step
   
   String fullMonthsData = "";
   for (MonthlyBusinessWorkDays data : monthlyBusinessWorkDays)
      fullMonthsData += data.getMonthlyData() + ((monthlyBusinessWorkDays.indexOf(data) != monthlyBusinessWorkDays.size() - 1) ? "," : "");
   String[] fullMonthsArr = fullMonthsData.split(",");
   
   Map<Long, List<WeeklyDefaultWorkHours>> serviceWklyWorkHrsMap = new HashMap<>();
   for (Long id : weeklyDefaultWorkHoursList.stream().map(data -> data.getService().getId()).collect(Collectors.toSet())) {
      serviceWklyWorkHrsMap.put(id, new ArrayList<>());
   }
   for (WeeklyDefaultWorkHours wklyWorkHrs : weeklyDefaultWorkHoursList) {
      serviceWklyWorkHrsMap.get(wklyWorkHrs.getService().getId()).add(wklyWorkHrs);
   }
   
   for (Long key : serviceWklyWorkHrsMap.keySet()) {
      Iterator<WeeklyDefaultWorkHours> it = serviceWklyWorkHrsMap.get(key).iterator();
      while (it.hasNext()) {
         WeeklyDefaultWorkHours weeklyDefaultWorkHours = it.next();
         String[] daySchedules = new String[]{"", weeklyDefaultWorkHours.getMonday(), weeklyDefaultWorkHours.getTuesday(), weeklyDefaultWorkHours.getWednesday(),
                         weeklyDefaultWorkHours.getThursday(), weeklyDefaultWorkHours.getFriday(), weeklyDefaultWorkHours.getSaturday(), weeklyDefaultWorkHours.getSunday()};
         System.out.println("\nweeklyDefaultWorkHours: " + weeklyDefaultWorkHours);
         int fullMonthsArrIndex = 0;
         int currentWeek = 0;
         int counter = 0;
         LocalDate currentWeekInfo = firstDay;
         while (currentWeek < numOfWeeks && fullMonthsArrIndex < fullMonthsArr.length - 6) {
            WeeklyCustomServiceProviderSchedule wklyProvSchd = new WeeklyCustomServiceProviderSchedule();
            
            for (LocalDate i = firstDay.plusDays(counter); i.isBefore(currentWeekInfo.plusDays(7)); i = i.plusDays(1)) {
               
               if (i.isBefore(weeklyDefaultWorkHours.getEffectiveBy()) || i.equals(weeklyDefaultWorkHours.getEffectiveBy())) {
                  System.out.println("week: " + currentWeek + ", i: " + i + ", serviceId: " + weeklyDefaultWorkHours.getService().getId());
                  
               } else {
                  if (it.hasNext()) {
                     weeklyDefaultWorkHours = it.next();
                     daySchedules = new String[]{"", weeklyDefaultWorkHours.getMonday(), weeklyDefaultWorkHours.getTuesday(), weeklyDefaultWorkHours.getWednesday(),
                                     weeklyDefaultWorkHours.getThursday(), weeklyDefaultWorkHours.getFriday(), weeklyDefaultWorkHours.getSaturday(), weeklyDefaultWorkHours.getSunday()};
                  } else break;
               }
               
            }
            currentWeek++;
            currentWeekInfo = currentWeekInfo.plusDays(7);
            counter = +7;
         }
         
         
      }
   }


//
//   String[] days = monthlyBusinessWorkDays.get().getMonthlyData().split(",");
//   List<LocalDate> offDays = new ArrayList<>();
//   for(int i=firstDay.getDayOfMonth()-1; i<days.length && i<firstDay.getDayOfMonth()+6; i++){
//      if (days[i] == "0") offDays.add(firstDay.withDayOfMonth(i+1));
//   }
//
//   List<WeeklyCustomServiceProviderSchedule> serProSchList = new ArrayList<>();
//
//   for(WeeklyDefaultWorkHours defWorkHours : weeklyDefaultWorkHoursList){
////      defWorkHours.
//   }
   
   
   return null;
}


@Override
public String generateADayInWeeklySchedule(LocalDate day, List<WeeklyDefaultWorkHours> weeklyDefaultWorkHours, List<CustomDays> customDays) {

//   String text = "08:00,11:30,13:00,15:30";
//
//   int bufferTime = 5;
//   int serviceTime = 25;
//   List<LocalTime> localTimes = Arrays.asList(text.split(",")).stream().map(str -> LocalTime.parse(str)).collect(Collectors.toList());
//
//   StringBuilder sb = new StringBuilder("");
//   LocalTime availableTime = localTimes.get(0);
//   int flag = 0;
//
//   while(availableTime.isBefore(localTimes.get(localTimes.size()-1)) && flag<localTimes.size()) {
//      if(availableTime.plusMinutes(serviceTime+bufferTime).isBefore(localTimes.get(flag+1)) || availableTime.plusMinutes(serviceTime+bufferTime).equals(localTimes.get(flag+1))) {
//         sb.append(availableTime.toString() + ",");
//         availableTime = availableTime.plusMinutes(serviceTime+bufferTime);
//      }else {
//         if(flag+2>localTimes.size()) break;
//         flag+=2;
//         availableTime = localTimes.get(flag);
//      }
//   }
//
//   return sb.toString();
   return null;
}

@Override
public WeeklyAppointmentsPerServiceProvider updateADayInWeeklySchedule(LocalDate date, WeeklyAppointmentsPerServiceProvider schedule, String newDaySchedule) {
   return null;
}

@Override
public WeeklyAppointmentsPerServiceProvider updateADayInWeeklySchedule(int day, WeeklyAppointmentsPerServiceProvider schedule, String newDaySchedule) {
   return null;
}

@Override
public String retrieveDaysSchedule(LocalDate date, WeeklyAppointmentsPerServiceProvider weeklyAppointmentsPerServiceProvider) {
   return null;
}

@Override
public String retrieveDaysSchedule(int day, WeeklyAppointmentsPerServiceProvider weeklyAppointmentsPerServiceProvider) {
   return null;
}

}
