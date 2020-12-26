package com.notsecure.Appointees.utilityservices;

import com.notsecure.Appointees.controller.WeeklyDefaultWorkHoursController;
import com.notsecure.Appointees.entity.*;
import com.notsecure.Appointees.service.CustomDaysService;
import com.notsecure.Appointees.service.MonthlyBusinessWorkDaysService;
import com.notsecure.Appointees.service.WeeklyDefaultWorkHoursService;
import lombok.SneakyThrows;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.FieldPredicates;
import org.jeasy.random.TypePredicates;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class MonthlyBusinessWorkDaysOperationsImplTest {
EasyRandom generator;
List<MonthlyBusinessWorkDays> monthlyBusinessWorkDaysList = new ArrayList<>();
List<WeeklyDefaultWorkHours> weeklyDefaultWorkHoursList = new ArrayList<>();
List<CustomDays> customDaysList = new ArrayList<>();

//@Spy
@InjectMocks
MonthlyBusinessWorkDaysOperationsImpl monthlyBusinessWorkDaysOperations;
@Mock
WeeklyDefaultWorkHoursService weeklyDefaultWorkHoursService;
@Mock
CustomDaysService customDaysService;
@Mock
MonthlyBusinessWorkDaysService monthlyBusinessWorkDaysService;

private CustomDays generateCustomDaysObject(Long id, String customDate, String dailyWorkHours, String dateCreated, String reason, Long branchId, Long companyId, Long serviceProviderId){
   Branch branch = (branchId==null) ? null : new Branch();
   if(branch!=null) branch.setId(branchId);
   Company company = (companyId==null) ? null : new Company();
   if(company!=null) company.setId(companyId);
   ServiceProvider serviceProvider = (serviceProviderId==null) ? null : new ServiceProvider();
   if(serviceProvider!=null) serviceProvider.setId(serviceProviderId);
   
   CustomDays result = new CustomDays();
   result.setId(id);
   result.setBranch(branch);
   result.setCompany(company);
   result.setServiceProvider(serviceProvider);
   result.setCustomDate(LocalDate.parse(customDate));
   result.setDailyWorkHours(dailyWorkHours);
   result.setDateCreated(LocalDateTime.parse(dateCreated));
   result.setReason(reason);
   return result;
}

private MonthlyBusinessWorkDays generateMonthlyBusinessWorkDaysObject(Long id, String firstDayOfMonth, String monthlyData, Long branchId, Long companyId){
   Branch branch = (branchId==null) ? null : new Branch();
   if(branch!=null) branch.setId(branchId);
   Company company = (companyId==null) ? null : new Company();
   if(company!=null) company.setId(companyId);
   MonthlyBusinessWorkDays result = new MonthlyBusinessWorkDays();
   result.setId(id);
   result.setFirstDayOfMonth(LocalDate.parse(firstDayOfMonth));
   result.setMonthlyData(monthlyData);
   result.setBranch(branch);
   result.setCompany(company);
   return result;
}

private WeeklyDefaultWorkHours generateWeeklyDefaultWorkHoursObject(Long id, String effectiveBy, String friday, String monday, String saturday, String sunday, String thursday, String tuesday, String wednesday, Long branchId, Long companyId, Long serviceId, Long serviceProviderId){
   Branch branch = (branchId==null) ? null : new Branch();
   if(branch!=null) branch.setId(branchId);
   Company company = (companyId==null) ? null : new Company();
   if(company!=null) company.setId(companyId);
   Service service = (serviceId==null) ? null : new Service();
   if(service!=null) service.setId(serviceId);
   ServiceProvider serviceProvider = (serviceProviderId==null) ? null : new ServiceProvider();
   if(serviceProvider!=null) serviceProvider.setId(serviceProviderId);
   
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
   
   //https://www.baeldung.com/java-easy-random
   EasyRandomParameters parameters = new EasyRandomParameters();
   parameters.stringLengthRange(2, 3);
   generator = new EasyRandom(parameters);
   
   /*
   Sample Object Generation
   MonthlyBusinessWorkDays monthlyBusinessWorkDays = generator.nextObject(MonthlyBusinessWorkDays.class);
   List<MonthlyBusinessWorkDays> data = generator.objects(MonthlyBusinessWorkDays.class, 5).collect(Collectors.toList());
    */
   
   //Monthly Sample Data
   monthlyBusinessWorkDaysList.add(generateMonthlyBusinessWorkDaysObject(700L,"2020-12-01","1,1,1,1,0,0,1,1,1,1,1,0,0,1,1,1,1,1,0,0,1,1,1,1,1,0,0,1,1,1,1",300L,500L));
   monthlyBusinessWorkDaysList.add(generateMonthlyBusinessWorkDaysObject(701L,"2020-12-01","1,1,1,1,1,0,1,1,1,1,1,1,0,1,1,1,1,1,1,0,1,1,1,1,1,1,0,1,1,1,1",300L,500L));
   //Weekly Sample Data
   weeklyDefaultWorkHoursList.add(generateWeeklyDefaultWorkHoursObject(1598L,"2019-05-30","08:00,15:00","08:00,15:00","08:00,15:00","closed","08:00,15:00","08:00,15:00","08:00,15:00",null,500L,null,null));
   weeklyDefaultWorkHoursList.add(generateWeeklyDefaultWorkHoursObject(1599L,"2020-01-01","closed","08:00,16:00","closed","closed","08:00,15:00","08:00,15:00","08:00,15:00",null,500L,null,null));
   weeklyDefaultWorkHoursList.add(generateWeeklyDefaultWorkHoursObject(1600L,"2021-04-30","08:00,12:00,13:00,18:00","08:00,12:00,13:00,18:01","08:00,13:00","08:00,13:00","08:00,12:00,13:00,18:04","08:00,12:00,13:00,18:05","08:00,12:00,13:00,18:06",null,500L,null,null));
   weeklyDefaultWorkHoursList.add(generateWeeklyDefaultWorkHoursObject(1601L,"2020-12-18","default","default","default","closed","16:01,16:01","08:00,08:29","default",300L,500L,null,null));
   weeklyDefaultWorkHoursList.add(generateWeeklyDefaultWorkHoursObject(1602L,"2030-01-30","08:00,16:00","default","default","default","default","08:00,08:31","default",300L,500L,null,null));
   weeklyDefaultWorkHoursList.add(generateWeeklyDefaultWorkHoursObject(1603L,"2025-04-30","closed","16:03,16:03","default","default","08:00,16:03","08:00,08:30","default",300L,500L,null,null));
   weeklyDefaultWorkHoursList.add(generateWeeklyDefaultWorkHoursObject(1604L,"2021-01-30","closed","default","default","default","default","default","default",301L,500L,null,null));
   weeklyDefaultWorkHoursList.add(generateWeeklyDefaultWorkHoursObject(1605L,"2021-04-30","08:00,12:00","08:00,12:00","closed","closed","08:00,12:00","default","default",300L,500L,1000L,1100L));
   weeklyDefaultWorkHoursList.add(generateWeeklyDefaultWorkHoursObject(1606L,"2021-04-30","12:30,16:00","12:30,16:00","default","default","12:00,14:00","closed","closed",300L,500L,1001L,1100L));
   weeklyDefaultWorkHoursList.add(generateWeeklyDefaultWorkHoursObject(1607L,"2020-07-19","closed","closed","closed","closed","08:00,15:00","08:00,15:00","08:00,20:00",null,500L,null,null));
   weeklyDefaultWorkHoursList.add(generateWeeklyDefaultWorkHoursObject(1608L,"2030-01-30","05:05,17:26","05:05,17:27","05:05,17:28","05:05,17:29","05:05,17:30","05:05,17:31","05:05,17:32",null,500L,null,null));
   //CustomDays sample data
   customDaysList.add(generateCustomDaysObject(600L,"2020-12-06","08:00,13:00","2020-12-10T11:18","Branch300L",300L,500L,null));
   customDaysList.add(generateCustomDaysObject(601L,"2020-12-01","12:00,20:01","2020-12-10T11:18","Branch-301L",301L,500L,null));
   customDaysList.add(generateCustomDaysObject(602L,"2020-12-31","closed","2020-12-10T11:18","COMPANY",null,500L,null));
   customDaysList.add(generateCustomDaysObject(603L,"2020-01-04","08:00,13:03","2020-12-10T11:18","COMPANY",null,500L,null));
   customDaysList.add(generateCustomDaysObject(604L,"2020-01-05","08:00,13:04","2020-12-10T11:18","COMPANY",null,500L,null));
   customDaysList.add(generateCustomDaysObject(605L,"2020-12-20","08:00,13:05","2020-12-10T11:18","COMPANY",null,500L,null));
   customDaysList.add(generateCustomDaysObject(606L,"2020-11-20","08:00,13:06","2020-12-10T11:18","COMPANY",null,500L,null));
   customDaysList.add(generateCustomDaysObject(607L,"2020-10-20","08:00,13:07","2020-12-10T11:18","Branch-301L",301L,500L,null));
   customDaysList.add(generateCustomDaysObject(608L,"2020-12-28","08:00,13:08","2020-12-10T11:18","COMPANY",null,500L,null));
   customDaysList.add(generateCustomDaysObject(609L,"2020-12-30","08:00,13:09","2020-12-10T11:18","Branch-301L",301L,500L,null));
   customDaysList.add(generateCustomDaysObject(610L,"2020-12-30","closed","2020-12-10T11:18","Branch300L",300L,500L,null));
   customDaysList.add(generateCustomDaysObject(611L,"2021-01-01","08:00,13:11","2020-12-10T11:18","Branch-301L",301L,500L,null));
   customDaysList.add(generateCustomDaysObject(612L,"2020-12-07","08:00,13:12","2020-12-10T11:18","COMPANY",null,500L,null));
   customDaysList.add(generateCustomDaysObject(613L,"2020-12-14","08:00,13:13","2020-12-10T11:18","COMPANY",null,500L,null));
}

@SneakyThrows
@Test
void createMonthlyYearDataForBranchFINAL() {
   
   List<WeeklyDefaultWorkHours> weeklyList = weeklyDefaultWorkHoursList.stream().filter(data -> Arrays.asList(new Long[]{1599L, 1607L, 1601L, 1600L, 1605L, 1606L, 1603L, 1602L, 1608L}).contains(data.getId())).sorted(Comparator.comparing(WeeklyDefaultWorkHours::getEffectiveBy)).collect(Collectors.toList());
   List<CustomDays> branchCD = customDaysList.stream().filter(data -> Arrays.asList(new Long[]{600L,610L}).contains(data.getId())).sorted(Comparator.comparing(CustomDays::getCustomDate)).collect(Collectors.toList());
   List<CustomDays> companyCD = customDaysList.stream().filter(data -> Arrays.asList(new Long[]{603L,604L,606L,612L,613L,605L,608L,602L}).contains(data.getId())).sorted(Comparator.comparing(CustomDays::getCustomDate)).collect(Collectors.toList());

   when(weeklyDefaultWorkHoursService.findWeeklyDefaultWorkHoursByEffectiveByIsAfterAndCompanyIdAndBranchIsNullOrBranchIdOrderByEffectiveBy(LocalDate.of(2020, 1, 1), 500L, 300L)).thenReturn(weeklyList);
   when(customDaysService.findByServiceProviderIsNullAndBranchIsNullAndCompanyIdAndCustomDateIsBetweenOrderByCustomDate(1L, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 12, 31))).thenReturn(companyCD);
   when(customDaysService.findByServiceProviderIsNullAndCompanyIdAndBranchIdAndCustomDateIsBetweenOrderByCustomDate(500L, 300L, LocalDate.of(2020, 1, 1), LocalDate.of(2020, 12, 31))).thenReturn(branchCD);
   
   Map<Integer, StringBuilder> map = monthlyBusinessWorkDaysOperations.createMonthlyYearDataForBranchFINAL(500L,300L,2020,1,12);
   System.out.println("map: " + map);
   assertEquals(map.get(1).toString(),"1,1,1,1,0,1,1,1,1,1,1,0,1,1,1,1,1,1,0,1,1,1,1,1,1,0,1,1,1,1,1,");
}

@Test
void updateADayInMonthlyData() {

}

@Test
void retrieveADay() {
   MonthlyBusinessWorkDays monthlyBusinessWorkDays = new MonthlyBusinessWorkDays(700L, LocalDate.parse("2020-12-01"), "1,1,1,1,0,0,1,1,1,1,1,0,0,1,1,1,1,1,0,0,1,1,1,1,1,0,0,1,1,1,1",null,null);
//   when(monthlyBusinessWorkDaysRepository.findMonthlyBusinessWorkDaysByBranchId(anyLong())).thenReturn(Collections.singletonList(monthlyBusinessWorkDays));
   String day = monthlyBusinessWorkDaysOperations.retrieveADay(monthlyBusinessWorkDays, LocalDate.parse("2020-12-05"));
   assertEquals(day, "0");
}

@Test
void retrieveADay_ConversionMismatchException() {
   MonthlyBusinessWorkDays monthlyBusinessWorkDays = new MonthlyBusinessWorkDays(799L, LocalDate.parse("2020-12-01"), "1,1,1,1,0,0,1,1,1,1,1,0,0,1,1,1,1,1,0,0,1,1,1,1,1,0,0,1,1,1,1,0,0",null,null);
   assertThrows(FormatFlagsConversionMismatchException.class, ()-> {
      monthlyBusinessWorkDaysOperations.retrieveADay(monthlyBusinessWorkDays, LocalDate.parse("2020-12-05"));
   });
}

@Test
void updateAllSingleDaysInMonthlyData_IndexOutOfBoundsException_LowerBound(){
   MonthlyBusinessWorkDays monthlyBusinessWorkDays = new MonthlyBusinessWorkDays(700L, LocalDate.parse("2020-12-01"), "1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1",null,null);
   assertThrows(IndexOutOfBoundsException.class, () -> {
      String month = monthlyBusinessWorkDaysOperations.updateAllSingleDaysInMonthlyData(monthlyBusinessWorkDays,0,0);
   });
}

@Test
void updateAllSingleDaysInMonthlyData_IndexOutOfBoundsException_UpperBound(){
   MonthlyBusinessWorkDays monthlyBusinessWorkDays = new MonthlyBusinessWorkDays(700L, LocalDate.parse("2020-12-01"), "1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1",null,null);
   assertThrows(IndexOutOfBoundsException.class, () -> {
      String month = monthlyBusinessWorkDaysOperations.updateAllSingleDaysInMonthlyData(monthlyBusinessWorkDays,8,0);
   });
}

@Test
void updateAllSingleDaysInMonthlyData() {
   MonthlyBusinessWorkDays monthlyBusinessWorkDays = new MonthlyBusinessWorkDays(700L, LocalDate.parse("2020-12-01"), "1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1",null,null);
   String month = monthlyBusinessWorkDaysOperations.updateAllSingleDaysInMonthlyData(monthlyBusinessWorkDays,5,0);
   assertEquals(month, "1,1,1,0,1,1,1,1,1,1,0,1,1,1,1,1,1,0,1,1,1,1,1,1,0,1,1,1,1,1,1");
}

@Test
void updateAllSingleDaysInMonthlyData_LowerEdgeTest() {
   MonthlyBusinessWorkDays monthlyBusinessWorkDays = new MonthlyBusinessWorkDays(700L, LocalDate.parse("2020-12-01"), "1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1",null,null);
   String month = monthlyBusinessWorkDaysOperations.updateAllSingleDaysInMonthlyData(monthlyBusinessWorkDays,2,0);
   assertEquals(month, "0,1,1,1,1,1,1,0,1,1,1,1,1,1,0,1,1,1,1,1,1,0,1,1,1,1,1,1,0,1,1");
}

@Test
void updateAllSingleDaysInMonthlyData_UpperEdgeTest() {
   MonthlyBusinessWorkDays monthlyBusinessWorkDays = new MonthlyBusinessWorkDays(700L, LocalDate.parse("2020-12-01"), "1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1",null,null);
   String month = monthlyBusinessWorkDaysOperations.updateAllSingleDaysInMonthlyData(monthlyBusinessWorkDays,4,0);
   assertEquals(month, "1,1,0,1,1,1,1,1,1,0,1,1,1,1,1,1,0,1,1,1,1,1,1,0,1,1,1,1,1,1,0");
}
}