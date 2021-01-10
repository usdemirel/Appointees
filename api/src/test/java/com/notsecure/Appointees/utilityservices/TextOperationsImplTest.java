package com.notsecure.Appointees.utilityservices;

import com.notsecure.Appointees.entity.*;
import com.notsecure.Appointees.service.CustomDaysService;
import com.notsecure.Appointees.service.MonthlyBusinessWorkDaysService;
import com.notsecure.Appointees.service.WeeklyDefaultWorkHoursService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

class TextOperationsImplTest {

List<MonthlyBusinessWorkDays> monthlyBusinessWorkDaysList = new ArrayList<>();
List<WeeklyDefaultWorkHours> weeklyDefaultWorkHoursList = new ArrayList<>();
List<CustomDays> customDaysList = new ArrayList<>();

@InjectMocks
TextOperationsImpl textOperations;

@Mock
WeeklyDefaultWorkHoursService weeklyDefaultWorkHoursService;

@Mock
MonthlyBusinessWorkDaysService monthlyBusinessWorkDaysService;

@Mock
CustomDaysService customDaysService;

private CustomDays generateCustomDaysObject(Long id, String customDate, String dailyWorkHours, String dateCreated, String reason, Long branchId, Long companyId, Long serviceId, Long serviceProviderId) {
   Branch branch = (branchId == null) ? null : new Branch();
   if (branch != null) branch.setId(branchId);
   Company company = (companyId == null) ? null : new Company();
   if (company != null) company.setId(companyId);
   Service service = (serviceId == null) ? null : new Service();
   if (service != null) service.setId(serviceId);
   ServiceProvider serviceProvider = (serviceProviderId == null) ? null : new ServiceProvider();
   if (serviceProvider != null) serviceProvider.setId(serviceProviderId);
   
   CustomDays result = new CustomDays();
   result.setId(id);
   result.setBranch(branch);
   result.setCompany(company);
   result.setService(service);
   result.setServiceProvider(serviceProvider);
   result.setCustomDate(LocalDate.parse(customDate));
   result.setDailyWorkHours(dailyWorkHours);
   result.setDateCreated(LocalDateTime.parse(dateCreated));
   result.setReason(reason);
   return result;
}

private MonthlyBusinessWorkDays generateMonthlyBusinessWorkDaysObject(Long id, String firstDayOfMonth, String monthlyData, Long branchId, Long companyId) {
   Branch branch = (branchId == null) ? null : new Branch();
   if (branch != null) branch.setId(branchId);
   Company company = (companyId == null) ? null : new Company();
   if (company != null) company.setId(companyId);
   MonthlyBusinessWorkDays result = new MonthlyBusinessWorkDays();
   result.setId(id);
   result.setFirstDayOfMonth(LocalDate.parse(firstDayOfMonth));
   result.setMonthlyData(monthlyData);
   result.setBranch(branch);
   result.setCompany(company);
   return result;
}

private WeeklyDefaultWorkHours generateWeeklyDefaultWorkHoursObject(Long id, String effectiveBy, String friday, String monday, String saturday, String sunday, String thursday, String tuesday, String wednesday, Long branchId, Long companyId, Long serviceId, Long serviceProviderId) {
   Branch branch = (branchId == null) ? null : new Branch();
   if (branch != null) branch.setId(branchId);
   Company company = (companyId == null) ? null : new Company();
   if (company != null) company.setId(companyId);
   Service service = (serviceId == null) ? null : new Service();
   if (service != null) service.setId(serviceId);
   ServiceProvider serviceProvider = (serviceProviderId == null) ? null : new ServiceProvider();
   if (serviceProvider != null) serviceProvider.setId(serviceProviderId);
   
   WeeklyDefaultWorkHours result2 = new WeeklyDefaultWorkHours();
   result2.setId(id);
   result2.setBranch(branch);
   result2.setCompany(company);
   result2.setService(service);
   result2.setServiceProvider(serviceProvider);
   result2.setEffectiveBy(LocalDate.parse(effectiveBy));
   result2.setMonday(monday);
   result2.setTuesday(tuesday);
   result2.setThursday(thursday);
   result2.setFriday(friday);
   result2.setSaturday(saturday);
   result2.setSunday(sunday);
   result2.setWednesday(wednesday);
   return result2;
}


@BeforeEach
void setUp() {
   MockitoAnnotations.initMocks(this);
   
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
   
   //Monthly Sample Data
   monthlyBusinessWorkDaysList.add(generateMonthlyBusinessWorkDaysObject(700L, "2020-12-01", "1,1,1,1,0,0,1,1,1,1,1,0,0,1,1,1,1,1,0,0,1,1,1,1,1,0,0,1,1,1,0", 300L, 500L));
   monthlyBusinessWorkDaysList.add(generateMonthlyBusinessWorkDaysObject(701L, "2020-12-01", "0,1,1,1,1,0,1,1,1,1,1,1,0,1,1,1,1,1,1,0,1,1,1,1,1,1,0,1,1,1,1", 300L, 500L));
   //Weekly Sample Data
   weeklyDefaultWorkHoursList.add(generateWeeklyDefaultWorkHoursObject(1600L,"2019-05-30","08:00,15:00","08:00,15:00","08:00,15:00","closed","08:00,15:00","08:00,15:00","08:00,15:00",null,500L,null,null));
   weeklyDefaultWorkHoursList.add(generateWeeklyDefaultWorkHoursObject(1601L,"2020-01-01","closed","08:00,16:00","closed","closed","08:00,15:00","08:00,15:00","08:00,15:00",null,500L,null,null));
   weeklyDefaultWorkHoursList.add(generateWeeklyDefaultWorkHoursObject(1602L,"2021-04-30","08:00,12:00,13:00,18:00","08:00,12:00,13:00,18:01","08:00,13:00","08:00,13:00","08:00,12:00,13:00,18:04","08:00,12:00,13:00,18:05","08:00,12:00,13:00,18:06",null,500L,null,null));
   weeklyDefaultWorkHoursList.add(generateWeeklyDefaultWorkHoursObject(1603L,"2020-07-19","closed","closed","closed","closed","08:00,15:00","08:00,15:00","08:00,20:00",null,500L,null,null));
   weeklyDefaultWorkHoursList.add(generateWeeklyDefaultWorkHoursObject(1604L,"2030-01-30","05:05,17:26","05:05,17:27","05:05,17:28","05:05,17:29","05:05,17:30","05:05,17:31","05:05,17:32",null,500L,null,null));
   weeklyDefaultWorkHoursList.add(generateWeeklyDefaultWorkHoursObject(1620L,"2020-12-18","default","default","default","closed","16:01,16:01","08:00,08:29","default",300L,500L,null,null));
   weeklyDefaultWorkHoursList.add(generateWeeklyDefaultWorkHoursObject(1621L,"2025-04-30","closed","16:03,16:03","default","default","08:00,16:03","08:00,08:30","default",300L,500L,null,null));
   weeklyDefaultWorkHoursList.add(generateWeeklyDefaultWorkHoursObject(1622L,"2030-01-30","08:00,16:00","default","default","default","default","08:00,08:31","default",300L,500L,null,null));
   weeklyDefaultWorkHoursList.add(generateWeeklyDefaultWorkHoursObject(1640L,"2021-01-30","closed","default","default","default","default","default","default",301L,500L,null,null));
   weeklyDefaultWorkHoursList.add(generateWeeklyDefaultWorkHoursObject(1650L,"2020-12-20","08:00,12:00,13:00,18:00","08:00,12:00","closed","closed","closed","08:00,11:59","10:00,12:00",300L,500L,1000L,1100L));
   weeklyDefaultWorkHoursList.get(weeklyDefaultWorkHoursList.size()-1).getService().setDuration(30);
   weeklyDefaultWorkHoursList.get(weeklyDefaultWorkHoursList.size()-1).getService().setBufferTime(30);
   weeklyDefaultWorkHoursList.add(generateWeeklyDefaultWorkHoursObject(1651L,"2021-04-30","08:00,12:00,13:00,18:00","08:00,12:00","closed","closed","closed","08:00,11:59","10:00,12:00",300L,500L,1000L,1100L));
   weeklyDefaultWorkHoursList.get(weeklyDefaultWorkHoursList.size()-1).getService().setDuration(30);
   weeklyDefaultWorkHoursList.get(weeklyDefaultWorkHoursList.size()-1).getService().setBufferTime(30);
   weeklyDefaultWorkHoursList.add(generateWeeklyDefaultWorkHoursObject(1652L,"2020-12-30","closed","12:30,16:00","08:00,12:00","12:00,15:00","08:00,12:00,13:00,18:00","12:59,16:00","12:00,19:00",300L,500L,1001L,1100L));
   weeklyDefaultWorkHoursList.get(weeklyDefaultWorkHoursList.size()-1).getService().setDuration(20);
   weeklyDefaultWorkHoursList.get(weeklyDefaultWorkHoursList.size()-1).getService().setBufferTime(10);
   weeklyDefaultWorkHoursList.add(generateWeeklyDefaultWorkHoursObject(1653L,"2021-04-30","19:00,21:00","12:30,16:00","08:00,12:00","closed","08:00,12:00,13:00,18:00","12:59,16:00","12:00,19:00",300L,500L,1001L,1100L));
   weeklyDefaultWorkHoursList.get(weeklyDefaultWorkHoursList.size()-1).getService().setDuration(20);
   weeklyDefaultWorkHoursList.get(weeklyDefaultWorkHoursList.size()-1).getService().setBufferTime(10);
   weeklyDefaultWorkHoursList.add(generateWeeklyDefaultWorkHoursObject(1654L,"2020-12-28","closed","closed","closed","closed","closed","closed","closed",300L,500L,1002L,1100L));
   weeklyDefaultWorkHoursList.get(weeklyDefaultWorkHoursList.size()-1).getService().setDuration(10);
   weeklyDefaultWorkHoursList.get(weeklyDefaultWorkHoursList.size()-1).getService().setBufferTime(30);
   weeklyDefaultWorkHoursList.add(generateWeeklyDefaultWorkHoursObject(1655L,"2021-04-30","07:07,12:07,13:00,18:00","07:00,12:00","13:00,22:00","07:00,12:00","19:00,22:00","07:00,12:00","07:00,12:00",300L,500L,1002L,1100L));
   weeklyDefaultWorkHoursList.get(weeklyDefaultWorkHoursList.size()-1).getService().setDuration(10);
   weeklyDefaultWorkHoursList.get(weeklyDefaultWorkHoursList.size()-1).getService().setBufferTime(30);
//CustomDays sample data
   customDaysList.add(generateCustomDaysObject(600L, "2020-12-06", "07:59,16:00", "2020-12-10T11:18", "Branch300", 300L, 500L, null, null));
   customDaysList.add(generateCustomDaysObject(601L, "2020-12-30", "07:59,16:01", "2020-12-10T11:18", "Branch300", 300L, 500L, null, null));
   customDaysList.add(generateCustomDaysObject(602L, "2020-01-01", "07:59,16:02", "2020-12-10T11:18", "GreedyBranch - New Years Day - Wednesday", 300L, 500L, null, null));
   customDaysList.add(generateCustomDaysObject(603L, "2020-01-31", "closed", "2020-12-10T11:18", "Friday, January 31 - Branch Special", 300L, 500L, null, null));
   customDaysList.add(generateCustomDaysObject(604L, "2020-07-04", "07:59,16:04", "2020-12-10T11:18", "GreedyBranch - Saturday, July 4 - Independence Day", 300L, 500L, null, null));
   customDaysList.add(generateCustomDaysObject(605L, "2020-11-26", "07:59,16:05", "2020-12-10T11:18", "GreedyBranch - Thursday, November 26 - Thanksgiving Day", 300L, 500L, null, null));
   customDaysList.add(generateCustomDaysObject(606L, "2020-12-01", "closed", "2020-12-10T11:18", "Tuesday, December 1 - Branch Special", 300L, 500L, null, null));
   customDaysList.add(generateCustomDaysObject(607L, "2020-12-25", "07:59,16:07", "2020-12-10T11:18", "GreedyBranch - Friday, December 25 - Christmas Day", 300L, 500L, null, null));
   customDaysList.add(generateCustomDaysObject(608L, "2020-12-31", "07:59,16:08", "2020-12-10T11:18", "GreedyBranch - Thursday, December 31 - Company Special", 300L, 500L, null, null));
   customDaysList.add(generateCustomDaysObject(630L, "2020-12-01", "12:00,20:01", "2020-12-10T11:18", "Branch-301", 301L, 500L, null, null));
   customDaysList.add(generateCustomDaysObject(631L, "2020-10-20", "08:00,13:07", "2020-12-10T11:18", "Branch-301", 301L, 500L, null, null));
   customDaysList.add(generateCustomDaysObject(632L, "2020-12-30", "08:00,13:09", "2020-12-10T11:18", "Branch-301", 301L, 500L, null, null));
   customDaysList.add(generateCustomDaysObject(633L, "2021-01-01", "08:00,13:11", "2020-12-10T11:18", "Branch-301", 301L, 500L, null, null));
   customDaysList.add(generateCustomDaysObject(660L, "2020-01-01", "closed", "2020-12-10T11:18", "New Years Day - Wednesday", null, 500L, null, null));
   customDaysList.add(generateCustomDaysObject(661L, "2020-01-20", "closed", "2020-12-10T11:18", "Monday, January 20 - Martin Luther King, Jr. Day", null, 500L, null, null));
   customDaysList.add(generateCustomDaysObject(662L, "2020-01-31", "08:01,12:02", "2020-12-10T11:18", "Friday, January 31 - Company Special", null, 500L, null, null));
   customDaysList.add(generateCustomDaysObject(663L, "2020-02-17", "closed", "2020-12-10T11:18", "Monday, February 17 - Presidents* Day", null, 500L, null, null));
   customDaysList.add(generateCustomDaysObject(664L, "2020-05-25", "closed", "2020-12-10T11:18", "Monday, May 25 - Memorial Day", null, 500L, null, null));
   customDaysList.add(generateCustomDaysObject(665L, "2020-07-04", "closed", "2020-12-10T11:18", "Saturday, July 4 - Independence Day", null, 500L, null, null));
   customDaysList.add(generateCustomDaysObject(666L, "2020-09-07", "closed", "2020-12-10T11:18", "Monday, September 7 - Labor Day", null, 500L, null, null));
   customDaysList.add(generateCustomDaysObject(667L, "2020-10-12", "closed", "2020-12-10T11:18", "Monday, October 12 - Columbus Day", null, 500L, null, null));
   customDaysList.add(generateCustomDaysObject(668L, "2020-11-11", "closed", "2020-12-10T11:18", "Wednesday, November 11 - Veterans Day", null, 500L, null, null));
   customDaysList.add(generateCustomDaysObject(669L, "2020-11-26", "closed", "2020-12-10T11:18", "Thursday, November 26 - Thanksgiving Day", null, 500L, null, null));
   customDaysList.add(generateCustomDaysObject(670L, "2020-12-31", "08:01,12:01", "2020-12-10T11:18", "Tuesday, December 1 - Company Special", null, 500L, null, null));
   customDaysList.add(generateCustomDaysObject(671L, "2020-12-25", "closed", "2020-12-10T11:18", "Friday, December 25 - Christmas Day", null, 500L, null, null));
   customDaysList.add(generateCustomDaysObject(672L, "2020-12-31", "closed", "2020-12-10T11:18", "Thursday, December 31 - Company Special", null, 500L, null, null));
   
   customDaysList.add(generateCustomDaysObject(685L,"2020-12-28","12:25,15:30","2020-12-10T11:18","adj",300L,500L,1001L,1100L));
   customDaysList.add(generateCustomDaysObject(686L,"2020-12-28","15:30,19:00","2020-12-10T11:18","another adj",300L,500L,1002L,1100L));
   customDaysList.add(generateCustomDaysObject(687L,"2020-12-29","06:00,08:00","2020-12-10T11:18","adj-2",300L,500L,1000L,1100L));
   customDaysList.add(generateCustomDaysObject(688L,"2020-12-29","closed","2020-12-10T11:18","another adj-2",300L,500L,1001L,1100L));
   customDaysList.add(generateCustomDaysObject(689L,"2020-12-29","closed","2020-12-10T11:18","another adj-2",300L,500L,1002L,1100L));
}

@Test
void generateWeeklyCustomServiceProviderSchedule(){
   
   List<WeeklyDefaultWorkHours> weeklyList = weeklyDefaultWorkHoursList.stream().filter(data -> Arrays.asList(new Long[]{1654L,1652L,1655L,1653L,1651L}).contains(data.getId())).sorted(Comparator.comparing(WeeklyDefaultWorkHours::getEffectiveBy)).collect(Collectors.toList());
   Optional<MonthlyBusinessWorkDays> monthlyBusinessWorkDays = Optional.of(monthlyBusinessWorkDaysList.stream().filter(data -> data.getId() == 700L).collect(Collectors.toList()).get(0));
   List<CustomDays> customDays = customDaysList.stream().filter(data -> Arrays.asList(new Long[]{685L,686L,687L,688L,689L}).contains(data.getId())).sorted(Comparator.comparing(CustomDays::getCustomDate)).collect(Collectors.toList());
   
   when(weeklyDefaultWorkHoursService.findWeeklyDefaultWorkHoursByServiceProviderIdAndCompanyIdAndEffectiveByIsAfterOrderByEffectiveBy(anyLong(),anyLong(),any())).thenReturn(weeklyList);
   when(monthlyBusinessWorkDaysService.findMonthlyBusinessWorkDaysByBranchIdAndFirstDayOfMonth(anyLong(), any())).thenReturn(monthlyBusinessWorkDays);
   when(monthlyBusinessWorkDaysService.findMonthlyBusinessWorkDaysByBranchIdAndFirstDayOfMonthIsBetweenOrderByFirstDayOfMonth(anyLong(),any(),any())).thenReturn(monthlyBusinessWorkDaysList);
   when(customDaysService.findByBranchIdAndServiceProviderIdAndCustomDateIsBetweenOrderByCustomDate(any(),any(),any(),any())).thenReturn(customDays);
   
   List<WeeklyCustomServiceProviderSchedule> weeklyCustomSPSchedule = textOperations
                   .generateWeeklyCustomServiceProviderSchedule(LocalDate.parse("2020-12-27"),1100L,500L,300L,1);
   
   weeklyCustomSPSchedule.forEach(data -> System.out.println("\nServiceId: " + data.getService().getId() + " date: " + data.getFirstDayOfWeek() + " -> " + data));
   
   assertEquals("08:00,09:00,10:00,11:00-06:00,07:00-10:00,11:00----",weeklyCustomSPSchedule.get(0).getMonday() + "-" + weeklyCustomSPSchedule.get(0).getTuesday() + "-" + weeklyCustomSPSchedule.get(0).getWednesday() + "-" + weeklyCustomSPSchedule.get(0).getThursday() + "-" + weeklyCustomSPSchedule.get(0).getFriday() + "-" + weeklyCustomSPSchedule.get(0).getSaturday() + "-" + weeklyCustomSPSchedule.get(0).getSunday());
   assertEquals("12:25,12:55,13:25,13:55,14:25,14:55--12:00,12:30,13:00,13:30,14:00,14:30,15:00,15:30,16:00,16:30,17:00,17:30,18:00,18:30---08:00,08:30,09:00,09:30,10:00,10:30,11:00,11:30-",weeklyCustomSPSchedule.get(1).getMonday() + "-" + weeklyCustomSPSchedule.get(1).getTuesday() + "-" + weeklyCustomSPSchedule.get(1).getWednesday() + "-" + weeklyCustomSPSchedule.get(1).getThursday() + "-" + weeklyCustomSPSchedule.get(1).getFriday() + "-" + weeklyCustomSPSchedule.get(1).getSaturday() + "-" + weeklyCustomSPSchedule.get(1).getSunday());
   assertEquals("15:30,16:10,16:50,17:30,18:10--07:00,07:40,08:20,09:00,09:40,10:20,11:00---13:00,13:40,14:20,15:00,15:40,16:20,17:00,17:40,18:20,19:00,19:40,20:20,21:00-",weeklyCustomSPSchedule.get(2).getMonday() + "-" + weeklyCustomSPSchedule.get(2).getTuesday() + "-" + weeklyCustomSPSchedule.get(2).getWednesday() + "-" + weeklyCustomSPSchedule.get(2).getThursday() + "-" + weeklyCustomSPSchedule.get(2).getFriday() + "-" + weeklyCustomSPSchedule.get(2).getSaturday() + "-" + weeklyCustomSPSchedule.get(2).getSunday());
}

@Test
void generateWeeklySchedule() {

   
//   textOperations.generateWeeklySchedule(1100L,500L,LocalDate.of(2020, 12, 20));

}

@Test
void generateADayInWeeklySchedule() {
}

@Test
void updateADayInWeeklySchedule() {
}

@Test
void testUpdateADayInWeeklySchedule() {
}

@Test
void retrieveDaysSchedule() {
}

@Test
void testRetrieveDaysSchedule() {
}
}