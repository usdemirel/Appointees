package com.notsecure.Appointees.utilityservices;

import com.notsecure.Appointees.entity.CustomDays;
import com.notsecure.Appointees.entity.MonthlyBusinessWorkDays;
import com.notsecure.Appointees.entity.WeeklyDefaultWorkHours;
import com.notsecure.Appointees.model.ErrorMessages;
import com.notsecure.Appointees.service.CustomDaysService;
import com.notsecure.Appointees.service.MonthlyBusinessWorkDaysService;
import com.notsecure.Appointees.service.WeeklyDefaultWorkHoursService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


public class MonthlyOLLLDDDBusinessWorkDaysOperationsImpl {

@Autowired
WeeklyDefaultWorkHoursService weeklyDefaultWorkHoursService;

@Autowired
CustomDaysService customDaysService;

@Autowired
MonthlyBusinessWorkDaysService monthlyBusinessWorkDaysService;


public String createMonthlyData(LocalDate firstDay, List<WeeklyDefaultWorkHours> weeklyDefaultWorkHours, List<CustomDays> customDays) throws NotFoundException {
   
   WeeklyDefaultWorkHours branch = null, company = null;
   for (WeeklyDefaultWorkHours temp : weeklyDefaultWorkHours) {
      if (temp.getBranch() == null && company == null) company = temp;
      else if (temp.getBranch() != null && branch == null) branch = temp;
      else if (branch != null && company != null) break;
   }
   
   if (branch == null)
      throw new NotFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage() + ": Please extend the Branch's Effectiveness day!");
   if (company == null)
      throw new NotFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage() + ": Please extend the Company's Effectiveness day!");
   
   String[] defaultDays = generateWeeklyDefaultWorkHours(branch, company);
   
   StringBuilder sb = new StringBuilder("");
   LocalDate finalDate = firstDay.plusDays(firstDay.lengthOfMonth());
   for (LocalDate day = firstDay; day.isBefore(finalDate); day = day.plusDays(1)) {
      sb.append((defaultDays[day.getDayOfWeek().getValue()].equals("closed") ? "0" : "1") + ",");
   }
   return sb.deleteCharAt(sb.lastIndexOf(",")).toString();
}

private String[] generateWeeklyDefaultWorkHours(WeeklyDefaultWorkHours branch, WeeklyDefaultWorkHours company) {
   return new String[]{
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


public MonthlyBusinessWorkDays updateADayInMonthlyData(MonthlyBusinessWorkDays oldMonthlyData, LocalDate dateToBeChanged, int newValue) {
   String[] daysArr = oldMonthlyData.getMonthlyData().split(",");
   daysArr[dateToBeChanged.getDayOfMonth() - 1] = String.valueOf(newValue);
   StringBuilder monthlyDataSb = new StringBuilder("");
   Arrays.asList(daysArr).stream().map(day -> day + ",").forEach(monthlyDataSb::append);
   return oldMonthlyData.setMonthlyData(monthlyDataSb.deleteCharAt(monthlyDataSb.length() - 1).toString());
}


public String retrieveADay(MonthlyBusinessWorkDays monthlyData, LocalDate date) {
   String[] daysArr = monthlyData.getMonthlyData().split(",");
   return daysArr[date.getDayOfMonth() - 1];
}


public String updateAllSingleDaysInMonthlyData(MonthlyBusinessWorkDays monthlyData, int day, int newValue) {
   int firstDay = (day - monthlyData.getFirstDayOfMonth().getDayOfWeek().getValue() + 7) % 7;
   String[] daysArr = monthlyData.getMonthlyData().split(",");
   while (firstDay <= monthlyData.getFirstDayOfMonth().lengthOfMonth()) {
      daysArr[firstDay] = String.valueOf(newValue);
      firstDay += 7;
   }
   System.out.println(Arrays.asList(daysArr).toString());
   return null;
}

//old
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
private String[] generateWeeklyDefaultWorkHours(WeeklyDefaultWorkHours weeklyDefaultWorkHours) {
   return new String[]{
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

//SELECT * FROM WEEKLY_DEFAULT_WORK_HOURS WHERE EFFECTIVE_BY > '2019-12-31' AND COMPANY_ID = 500 AND BRANCH_ID IS NULL OR BRANCH_ID = 300 ORDER BY EFFECTIVE_BY;
/*
TYPE	   ID	   EFFECTIVE_BY	WEDNESDAY	                  BRANCH_ID	COMPANY_ID	SERVICE_ID	SERVICE_PROVIDER_ID
COMPANY	1599	1/12/2020	   08:00,15:00	                  null	      500	      null	      null
COMPANY	1607	7/19/2020	   08:00,20:00	                  null	      500	      null	      null
BRANCH	1601	12/20/2020	   default	                     300	      500	      null	      null
SERVICE	1605	4/30/2021	   default	                     300	      500	      1000	      1100
SERVICE	1606	4/30/2021	   null	                        300	      500	      1001	      1100
COMPANY	1600	4/30/2021	   08:00,12:00,13:00,18:00	      null	      500	      null	      null
BRANCH	1603	4/30/2025	   default	                     300	      500	      null	      null
BRANCH	1602	1/30/2030	   default	                     300	      500	      null	      null

 */

//old
// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
private List<WeeklyDefaultWorkHours> generateWeeklyDefaultWorkHoursForBranch(Long companyId, Long branchId, int year, int initMonth, int endMonth) {
   List<WeeklyDefaultWorkHours> result = new ArrayList<>();
   LocalDate lastDay = LocalDate.of(year, endMonth, LocalDate.of(year, endMonth, 01).lengthOfMonth());
   List<WeeklyDefaultWorkHours> branchAndCompanyWeeklyDefaultWorkHoursList = weeklyDefaultWorkHoursService.findWeeklyDefaultWorkHoursByEffectiveByIsAfterAndCompanyIdAndBranchIsNullOrBranchIdOrderByEffectiveBy(LocalDate.of(year, initMonth, 1), companyId, branchId);
   List<CustomDays> customDays = customDaysService.findByServiceProviderIsNullAndBranchIsNullAndCompanyIdAndCustomDateIsBetweenOrderByCustomDate(companyId, LocalDate.of(year, initMonth, 1), lastDay);
   
   List<WeeklyDefaultWorkHours> companyWeeklyDefaultWorkHoursList = branchAndCompanyWeeklyDefaultWorkHoursList.stream().filter(data -> data.getBranch() == null).collect(Collectors.toList());
   List<WeeklyDefaultWorkHours> branchWeeklyDefaultWorkHoursList = branchAndCompanyWeeklyDefaultWorkHoursList.stream().filter(data -> data.getBranch() != null && data.getService() == null).collect(Collectors.toList());
   
   
   int branchIndex = 0, branchListSize = branchWeeklyDefaultWorkHoursList.size();
   for (WeeklyDefaultWorkHours company : companyWeeklyDefaultWorkHoursList) {
      while (branchWeeklyDefaultWorkHoursList.get(branchIndex).getEffectiveBy().isBefore(company.getEffectiveBy()) || branchWeeklyDefaultWorkHoursList.get(branchIndex).getEffectiveBy().equals(company.getEffectiveBy()))
         branchIndex++;
      while (company.getEffectiveBy().isBefore(branchWeeklyDefaultWorkHoursList.get(branchIndex).getEffectiveBy()) && branchIndex < branchListSize) {
         WeeklyDefaultWorkHours branch = new WeeklyDefaultWorkHours(branchWeeklyDefaultWorkHoursList.get(branchIndex));
         branch.setEffectiveBy(company.getEffectiveBy());
         branchWeeklyDefaultWorkHoursList.add(branch);
         break;
      }
   }
   
   
   Collections.sort(branchWeeklyDefaultWorkHoursList, Comparator.comparing(WeeklyDefaultWorkHours::getEffectiveBy));
   
   List<WeeklyDefaultWorkHours> branchWorkHours = new ArrayList<>(branchAndCompanyWeeklyDefaultWorkHoursList);
   
   int companyIndex = 0;
   for (WeeklyDefaultWorkHours branch : branchWorkHours) {
      if (companyIndex < companyWeeklyDefaultWorkHoursList.size() && companyWeeklyDefaultWorkHoursList.get(companyIndex).getEffectiveBy().isBefore(branch.getEffectiveBy()))
         companyIndex++;
      else if (companyIndex < companyWeeklyDefaultWorkHoursList.size()){
//         branch = generateBranchWeeklyDefaultWorkHours(branch, companyWeeklyDefaultWorkHoursList.get(companyIndex++));
         WeeklyDefaultWorkHours company = companyWeeklyDefaultWorkHoursList.get(companyIndex++);
         branch.setSunday(branch.getSunday().equals("default") ? company.getSunday() : branch.getSunday());
         branch.setMonday(branch.getMonday().equals("default") ? company.getMonday() : branch.getMonday());
         branch.setTuesday(branch.getTuesday().equals("default") ? company.getTuesday() : branch.getTuesday());
         branch.setWednesday(branch.getWednesday().equals("default") ? company.getWednesday() : branch.getWednesday());
         branch.setThursday(branch.getThursday().equals("default") ? company.getThursday() : branch.getThursday());
         branch.setFriday(branch.getFriday().equals("default") ? company.getFriday() : branch.getFriday());
         branch.setSaturday(branch.getSaturday().equals("default") ? company.getSaturday() : branch.getSaturday());
         

      }
   }
   
   branchAndCompanyWeeklyDefaultWorkHoursList.forEach(data -> System.out.println("+*+> branchWeeklyDefaultWorkHoursList: " + data.getId() + "\t" + data.getEffectiveBy() + "\t" + data.getMonday()));
   branchWorkHours.forEach(data -> System.out.println("+*+------> branchWorkHours: " + data.getId() + "\t" + data.getEffectiveBy() + "\t" + data.getMonday()));
   
   
   branchWeeklyDefaultWorkHoursList.forEach(data -> System.out.println("--> " + data.getEffectiveBy() + " - " + data.getId() + " -W " + data.getWednesday() + " -Th " + data.getThursday()));
   
//   System.out.println("caling for custom days..\n");
//   filterCustomDaysForBranch(companyId, branchId, year, initMonth, endMonth).forEach(data -> System.out.println("-> bRANCHCustomDayAfferEffects: " + data.getId() + "\t" + data.getCustomDate()));
   
   return branchWeeklyDefaultWorkHoursList;
}

//old
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
private WeeklyDefaultWorkHours generateBranchWeeklyDefaultWorkHours(WeeklyDefaultWorkHours branch, WeeklyDefaultWorkHours company) {
   
   WeeklyDefaultWorkHours result = new WeeklyDefaultWorkHours(branch);
   
   result.setSunday(branch.getSunday().equals("default") ? company.getSunday() : branch.getSunday());
   result.setMonday(branch.getMonday().equals("default") ? company.getMonday() : branch.getMonday());
   result.setTuesday(branch.getTuesday().equals("default") ? company.getTuesday() : branch.getTuesday());
   result.setWednesday(branch.getWednesday().equals("default") ? company.getWednesday() : branch.getWednesday());
   result.setThursday(branch.getThursday().equals("default") ? company.getThursday() : branch.getThursday());
   result.setFriday(branch.getFriday().equals("default") ? company.getFriday() : branch.getFriday());
   result.setSaturday(branch.getSaturday().equals("default") ? company.getSaturday() : branch.getSaturday());
   
   return result;
}

//old
// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
private List<CustomDays> filterCustomDaysForBranch(Long companyId, Long branchId, int year, int initMonth, int endMonth) {
   
   LocalDate lastDay = LocalDate.of(year, endMonth, LocalDate.of(year, endMonth, 01).lengthOfMonth());
   List<WeeklyDefaultWorkHours> branchAndCompanyWeeklyDefaultWorkHoursList = weeklyDefaultWorkHoursService.findWeeklyDefaultWorkHoursByEffectiveByIsAfterAndCompanyIdAndBranchIsNullOrBranchIdOrderByEffectiveBy(LocalDate.of(year, initMonth, 1), companyId, branchId);
   
   branchAndCompanyWeeklyDefaultWorkHoursList.forEach(data -> System.out.println("----> branchAndCompanyWeeklyDefaultWorkHoursList: " + data.getId() + "\t" + data.getEffectiveBy() + "\t" + data.getMonday()));
   
   List<CustomDays> companyCustomDays = customDaysService.findByServiceProviderIsNullAndBranchIsNullAndCompanyIdAndCustomDateIsBetweenOrderByCustomDate(companyId, LocalDate.of(year, initMonth, 1), lastDay);
   List<CustomDays> branchCustomDays = customDaysService.findByServiceProviderIsNullAndCompanyIdAndBranchIdAndCustomDateIsBetweenOrderByCustomDate(companyId, branchId, LocalDate.of(year, initMonth, 1), lastDay);
   List<LocalDate> branchCustomDaysKeys = branchCustomDays.stream().map(data -> data.getCustomDate()).collect(Collectors.toList());
   List<WeeklyDefaultWorkHours> branchWeeklyDefaultWorkHoursList = branchAndCompanyWeeklyDefaultWorkHoursList.stream().filter(data -> data.getBranch() != null && data.getService() == null).collect(Collectors.toList());
   
   branchWeeklyDefaultWorkHoursList.forEach(data -> System.out.println("----> branchWeeklyDefaultWorkHoursList: " + data.getId() + "\t" + data.getEffectiveBy() + "\t" + data.getMonday()));
   System.out.print("\n Keys: ");
   branchCustomDaysKeys.forEach(data -> System.out.print(data + ", "));
   System.out.println();
   
   companyCustomDays.forEach(data -> System.out.println("-> Company's DATA: " + data.getId() + "\t" + data.getCustomDate()));
   branchCustomDays.forEach(data -> System.out.println("-> CustomDayBEFOREEffects: " + data.getId() + "\t" + data.getCustomDate()));
   
   int i = 0, j = 0, branchWeeklyDefaultWorkHoursListSize = branchWeeklyDefaultWorkHoursList.size();
   while (i < branchWeeklyDefaultWorkHoursListSize && j < companyCustomDays.size()) {
      String[] days = generateWeeklyDefaultWorkHours(branchWeeklyDefaultWorkHoursList.get(i));
      System.out.println("******** id: " + branchWeeklyDefaultWorkHoursList.get(i).getId() + " i: " + i + "days: " + Arrays.toString(days));
      while (j < companyCustomDays.size()) {
         if (companyCustomDays.get(j).getCustomDate().isBefore(branchWeeklyDefaultWorkHoursList.get(i).getEffectiveBy().plusDays(1))) {
            if (!branchCustomDaysKeys.contains(companyCustomDays.get(j).getCustomDate()) && days[companyCustomDays.get(j).getCustomDate().getDayOfWeek().getValue()].equalsIgnoreCase("default")) {
               branchCustomDays.add(companyCustomDays.get(j++));
            } else j++;
         } else {
            i++;
            break;
         }
      }
   }
   Collections.sort(branchCustomDays, Comparator.comparing(CustomDays::getCustomDate));
   return branchCustomDays;
}


/*
SELECT * FROM WEEKLY_DEFAULT_WORK_HOURS;
SELECT * FROM WEEKLY_DEFAULT_WORK_HOURS WHERE COMPANY_ID = 500 AND BRANCH_ID IS NULL OR BRANCH_ID = 300 AND SERVICE_ID IS NULL ORDER BY EFFECTIVE_BY;
SELECT * FROM WEEKLY_DEFAULT_WORK_HOURS WHERE BRANCH_ID = 300 AND SERVICE_ID IS NULL ORDER BY EFFECTIVE_BY;
 */
public Map<Integer, String> createMonthlyYearDataForCompany(Long companyId, int year, int initMonth, int endMonth) throws NotFoundException {
   generateWeeklyDefaultWorkHoursForBranch(companyId, 300L, 2020, 1, 12);
   /*
   DATA COLLECTION
    */
   LocalDate lastDay = LocalDate.of(year, endMonth, LocalDate.of(year, endMonth, 01).lengthOfMonth());
   List<WeeklyDefaultWorkHours> companyWeeklyDefaultWorkHoursList = weeklyDefaultWorkHoursService.findWeeklyDefaultWorkHoursByEffectiveByIsAfterAndCompanyIdAndBranchIsNullOrBranchIdOrderByEffectiveBy(LocalDate.of(year, initMonth, 1), companyId, null);
   List<CustomDays> customDays = customDaysService.findByServiceProviderIsNullAndBranchIsNullAndCompanyIdAndCustomDateIsBetweenOrderByCustomDate(companyId, LocalDate.of(year, initMonth, 1), lastDay);
   if (companyWeeklyDefaultWorkHoursList.size() == 0)
      throw new NotFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
   ArrayDeque<CustomDays> customDayArrayDeque = new ArrayDeque<>(customDays.stream().filter(data -> data.getCustomDate().isAfter(LocalDate.of(year, initMonth, 1).minusDays(1))).collect(Collectors.toList()));
   
   /*
   MAP INITIALIZATION
    */
   Map<Integer, StringBuilder> companyMap = new HashMap<>();
   for (int i = initMonth; i <= endMonth; i++) companyMap.put(i, new StringBuilder(""));
   
   /*
   MAPPPING
    */
   int counter = 0;
   for (WeeklyDefaultWorkHours company : companyWeeklyDefaultWorkHoursList) {
      String[] companyWeeklyData = generateWeeklyDefaultWorkHours(company);
      for (LocalDate day = LocalDate.of(year, initMonth, 1).plusDays(counter); day.isBefore(company.getEffectiveBy().plusDays(1)) && day.isBefore(lastDay.plusDays(1)); day = day.plusDays(1)) {
         String dayValue = "";
         if (!customDayArrayDeque.isEmpty() && customDayArrayDeque.getFirst().getCustomDate().equals(day)) {
            dayValue = customDayArrayDeque.remove().getDailyWorkHours().equals("closed") ? "0," : "1,";
         } else dayValue = companyWeeklyData[day.getDayOfWeek().getValue()] + ",";
         companyMap.put(day.getMonthValue(), companyMap.get(day.getMonthValue()).append(dayValue));
         counter++;
      }
   }
   
   System.out.println(companyMap);
   
   return null;
}


public Map<Integer, String> createMonthlyYearDataForBranch(Long companyId, Long branchId, int year, int initMonth, int endMonth) throws NotFoundException {

   /*
   DATA COLLECTION*******
    */
   List<MonthlyBusinessWorkDays> monthlyBusinessWorkDays = monthlyBusinessWorkDaysService.findMonthlyBusinessWorkDaysByBranchIsNullAndCompanyIdAndFirstDayOfMonthIsBetweenOrderByFirstDayOfMonth(
                   companyId, LocalDate.of(year, initMonth, 1), LocalDate.of(year, endMonth, 2)
   );
   
   Map<Integer, String> companyMonthlyMap = new HashMap<>();
   monthlyBusinessWorkDays.forEach(data -> companyMonthlyMap.put(data.getFirstDayOfMonth().getMonthValue(), data.getMonthlyData()));
   for (int i = initMonth; i <= endMonth; i++)
      if (!companyMonthlyMap.containsKey(i)) throw new NotFoundException("Missing Company Monthly Data");
   
   
   LocalDate lastDay = LocalDate.of(year, endMonth, LocalDate.of(year, endMonth, 01).lengthOfMonth());
   List<WeeklyDefaultWorkHours> branchWeeklyDefaultWorkHoursList = weeklyDefaultWorkHoursService.findWeeklyDefaultWorkHoursByEffectiveByIsAfterAndCompanyIdAndBranchIsNullOrBranchIdOrderByEffectiveBy(LocalDate.of(year, initMonth, 1), companyId, branchId);
   List<CustomDays> customDays = customDaysService.findByServiceProviderIsNullAndCompanyIdAndBranchIdAndCustomDateIsBetweenOrderByCustomDate(companyId, branchId, LocalDate.of(year, initMonth, 1), lastDay);
   if (branchWeeklyDefaultWorkHoursList.size() == 0)
      throw new NotFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
   Map<LocalDate, String> customDayMap = customDays.stream().filter(data -> data.getCustomDate().isAfter(LocalDate.of(year, initMonth, 1).minusDays(1)))
                   .collect(Collectors.toMap(data -> data.getCustomDate(), data -> data.getDailyWorkHours()));

   /*
   MAP INITIALIZATION
    */
   Map<Integer, StringBuilder> branchMap = new HashMap<>();
   for (int i = initMonth; i <= endMonth; i++) branchMap.put(i, new StringBuilder(""));

   /*
   MAPPPING
    */
   int counter = 0;
   for (WeeklyDefaultWorkHours branch : branchWeeklyDefaultWorkHoursList) {
      String[] companyWeeklyData = generateWeeklyDefaultWorkHours(branch);
      
      for (LocalDate day = LocalDate.of(year, initMonth, 1).plusDays(counter); day.isBefore(branch.getEffectiveBy().plusDays(1)) && day.isBefore(lastDay.plusDays(1)); day = day.plusDays(1)) {
         String dayValue = "";
         if (customDayMap.containsKey(day)) {
            dayValue = customDayMap.remove(day).equals("closed") ? "0," : "1,";
         } else dayValue = companyWeeklyData[day.getDayOfWeek().getValue()] + ",";
         branchMap.put(day.getMonthValue(), branchMap.get(day.getMonthValue()).append(dayValue));
         counter++;
      }
   }
   
   System.out.println(branchMap);
   return null;
}

public Map<Integer, String> createMonthlyYearDataForBranchFINAL(Long companyId, Long branchId, int year, int initMonth, int endMonth) throws NotFoundException {
   
   System.out.println("createMonthlyYearDataForBranchFINAL HAS BEEN CALLED!");
   List<WeeklyDefaultWorkHours> branchWeeklyHours = generateWeeklyDefaultWorkHoursForBranch(companyId, branchId, year, initMonth, endMonth);
   branchWeeklyHours.forEach(data -> System.out.println("Id: " + data.getId() + " Eff: " + data.getEffectiveBy() + " Mon: " + data.getMonday() + " Tue: " + data.getTuesday() + " Wed: " + data.getWednesday() + " Thu: " + data.getThursday() + " Fri: " + data.getFriday() + " Sat: " + data.getSaturday()));
   List<CustomDays> branchCustomDays = filterCustomDaysForBranch(companyId, branchId, year, initMonth, endMonth);
   
      /*
   MAP INITIALIZATION
    */
   Map<Integer, StringBuilder> branchMap = new HashMap<>();
   for (int i = initMonth; i <= endMonth; i++) branchMap.put(i, new StringBuilder(""));

   /*
   MAPPPING
    */
   int counter = 0;
   LocalDate lastDay = LocalDate.of(year, endMonth, LocalDate.of(year, endMonth, 01).lengthOfMonth());
   
   for (WeeklyDefaultWorkHours branch : branchWeeklyHours) {
      String[] branchWeeklyData = generateWeeklyDefaultWorkHours(branch);
      for (LocalDate day = LocalDate.of(year, initMonth, 1).plusDays(counter); day.isBefore(branch.getEffectiveBy().plusDays(1)) && day.isBefore(lastDay.plusDays(1)); day = day.plusDays(1)) {
         String dayValue = branchWeeklyData[day.getDayOfWeek().getValue()] + ",";
         branchMap.put(day.getMonthValue(), branchMap.get(day.getMonthValue()).append(dayValue));
         counter++;
      }
   }
   
   System.out.println("branchMap: " + branchMap);
   
   
   return null;
}

}


//   keep it for now..
//   Object[][] days = {{"monday",1},{"tuesday",2},{"wednesday,3"},{"thursday",4},{"friday",5},{"saturday",6},{"sunday",7}};
//   Map<String, Integer> dayMappping = Stream.of(Arrays.asList(days)).collect(Collectors.toMap(data -> (String) data.get(0)[0], data -> (Integer) data.get(0)[1]));
