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

@Service
public class MonthlyBusinessWorkDaysOperationsImpl implements MonthlyBusinessWorkDaysOperations {

@Autowired
WeeklyDefaultWorkHoursService weeklyDefaultWorkHoursService;
@Autowired
CustomDaysService customDaysService;
@Autowired
MonthlyBusinessWorkDaysService monthlyBusinessWorkDaysService;

/*
SELECT * FROM WEEKLY_DEFAULT_WORK_HOURS WHERE EFFECTIVE_BY > '2019-12-31' AND COMPANY_ID = 500 AND BRANCH_ID IS NULL OR BRANCH_ID = 300 AND SERVICE_ID IS NULL ORDER BY EFFECTIVE_BY;
SELECT * FROM WEEKLY_DEFAULT_WORK_HOURS WHERE EFFECTIVE_BY > '2019-12-31' AND COMPANY_ID = 500 AND BRANCH_ID IS NULL AND SERVICE_ID IS NULL ORDER BY EFFECTIVE_BY;
SELECT * FROM WEEKLY_DEFAULT_WORK_HOURS WHERE EFFECTIVE_BY > '2019-12-31' AND COMPANY_ID = 500 AND BRANCH_ID = 300 AND SERVICE_ID IS NULL ORDER BY EFFECTIVE_BY;
 */
/*
The purpose of this method is to replace default hours with the company's set work Hours.
For example, 2020-01-11 monday is set to default for a branch, and the company's work hour has been set to 08:00 to 16:00 for the given day.
Branch's default hour will be replaced with the company's work hours.
 */
public List<WeeklyDefaultWorkHours> generateWeeklyBranchWorkHoursBasedOnCompanyHours(Long companyId, Long branchId, int year, int initMonth, int endMonth) {
   //pulled company and branch data all at once, then, separated them.
   //output: companyWeeklyDefaultWorkHoursList and branchWeeklyDefaultWorkHoursList
   List<WeeklyDefaultWorkHours> branchAndCompanyWeeklyDefaultWorkHoursList = weeklyDefaultWorkHoursService.findWeeklyDefaultWorkHoursByEffectiveByIsAfterAndCompanyIdAndBranchIsNullOrBranchIdOrderByEffectiveBy(LocalDate.of(year, initMonth, 1), companyId, branchId);
   List<WeeklyDefaultWorkHours> companyWeeklyDefaultWorkHoursList = branchAndCompanyWeeklyDefaultWorkHoursList.stream().filter(data -> data.getBranch() == null).collect(Collectors.toList());
   List<WeeklyDefaultWorkHours> branchWeeklyDefaultWorkHoursList = branchAndCompanyWeeklyDefaultWorkHoursList.stream().filter(data -> data.getBranch() != null && data.getService() == null).collect(Collectors.toList());
   
   //when necessary, created duplicated of branch week default work hours data to have a match with the company's default work hours
   //output: sorted branchWeeklyDefaultWorkHoursList
   int branchIndex = 0, branchListSize = branchWeeklyDefaultWorkHoursList.size();
   for (WeeklyDefaultWorkHours company : companyWeeklyDefaultWorkHoursList) {
      while (branchIndex < branchListSize && branchWeeklyDefaultWorkHoursList.get(branchIndex).getEffectiveBy().isBefore(company.getEffectiveBy().plusDays(1)))
         branchIndex++;
      while (branchIndex < branchListSize && company.getEffectiveBy().isBefore(branchWeeklyDefaultWorkHoursList.get(branchIndex).getEffectiveBy().plusDays(1))) {
         branchWeeklyDefaultWorkHoursList.add(new WeeklyDefaultWorkHours(branchWeeklyDefaultWorkHoursList.get(branchIndex)).setEffectiveBy(company.getEffectiveBy()));
         break;
      }
   }
   Collections.sort(branchWeeklyDefaultWorkHoursList, Comparator.comparing(WeeklyDefaultWorkHours::getEffectiveBy));
   
   //Here I combined company and branch's weekly data based on Weekly Data's effectiveBy date
   int companyIndex = 0;
   for (WeeklyDefaultWorkHours branch : branchWeeklyDefaultWorkHoursList) {
      int currentBranchIndex = branchWeeklyDefaultWorkHoursList.indexOf(branch);
      if (companyIndex < companyWeeklyDefaultWorkHoursList.size() && companyWeeklyDefaultWorkHoursList.get(companyIndex).getEffectiveBy().isBefore(branch.getEffectiveBy()))
         companyIndex++;
      else if (companyIndex < companyWeeklyDefaultWorkHoursList.size()) {
         if (currentBranchIndex + 1 < branchWeeklyDefaultWorkHoursList.size() && companyWeeklyDefaultWorkHoursList.get(companyIndex).getEffectiveBy().isBefore(branchWeeklyDefaultWorkHoursList.get(currentBranchIndex + 1).getEffectiveBy()))
            companyIndex++;
         WeeklyDefaultWorkHours combinedBranch = generateBranchWeeklyDefaultWorkHours(branch, companyWeeklyDefaultWorkHoursList.get(companyIndex));
         branchWeeklyDefaultWorkHoursList.set(currentBranchIndex, combinedBranch);
      }
   }
   // here I cleaned the data from possible duplicates.
   int i = 0;
   while (i + 1 < branchWeeklyDefaultWorkHoursList.size())
      if (branchWeeklyDefaultWorkHoursList.get(i).getEffectiveBy().equals(branchWeeklyDefaultWorkHoursList.get(++i).getEffectiveBy()))
         branchWeeklyDefaultWorkHoursList.remove(i);
   
   return branchWeeklyDefaultWorkHoursList;
}

private List<CustomDays> filterCustomDaysForBranch(Long companyId, Long branchId, int year, int initMonth, int endMonth) {
   LocalDate lastDay = LocalDate.of(year, endMonth, LocalDate.of(year, endMonth, 01).lengthOfMonth());
   List<WeeklyDefaultWorkHours> branchAndCompanyWeeklyDefaultWorkHoursList = weeklyDefaultWorkHoursService.findWeeklyDefaultWorkHoursByEffectiveByIsAfterAndCompanyIdAndBranchIsNullOrBranchIdOrderByEffectiveBy(LocalDate.of(year, initMonth, 1), companyId, branchId);
   List<CustomDays> companyCustomDays = customDaysService.findByServiceProviderIsNullAndBranchIsNullAndCompanyIdAndCustomDateIsBetweenOrderByCustomDate(companyId, LocalDate.of(year, initMonth, 1), lastDay);
   List<CustomDays> branchCustomDays = customDaysService.findByServiceProviderIsNullAndCompanyIdAndBranchIdAndCustomDateIsBetweenOrderByCustomDate(companyId, branchId, LocalDate.of(year, initMonth, 1), lastDay);
//   companyCustomDays.forEach(data -> System.out.println("companyCustomDays: " + data.getId()));
//   branchCustomDays.forEach(data -> System.out.println("branchCustomDays: " + data.getId()));
   
   List<LocalDate> branchCustomDaysKeys = branchCustomDays.stream().map(data -> data.getCustomDate()).collect(Collectors.toList());
   List<WeeklyDefaultWorkHours> branchWeeklyDefaultWorkHoursList = branchAndCompanyWeeklyDefaultWorkHoursList.stream().filter(data -> data.getBranch() != null && data.getService() == null).collect(Collectors.toList());
   
   int i = 0, j = 0, branchWeeklyDefaultWorkHoursListSize = branchWeeklyDefaultWorkHoursList.size();
   while (i < branchWeeklyDefaultWorkHoursListSize && j < companyCustomDays.size()) {
      String[] days = generateWeeklyDefaultWorkHours(branchWeeklyDefaultWorkHoursList.get(i));
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


@Override
public Map<Integer, String> createMonthlyYearDataForBranchFINAL(Long companyId, Long branchId, int year, int initMonth, int endMonth) throws NotFoundException {
   List<WeeklyDefaultWorkHours> branchWeeklyHours = generateWeeklyBranchWorkHoursBasedOnCompanyHours(companyId, branchId, year, initMonth, endMonth);
   List<CustomDays> branchCustomDays = filterCustomDaysForBranch(companyId, branchId, year, initMonth, endMonth);
//   branchWeeklyHours.forEach(data -> System.out.println("generatedWeeklyBranchWorkHoursBasedOnCompanyHours -> Id: " + data.getId() + " Eff: " + data.getEffectiveBy() + " Mon: " + data.getMonday() + " Tue: " + data.getTuesday() + " Wed: " + data.getWednesday() + " Thu: " + data.getThursday() + " Fri: " + data.getFriday() + " Sat: " + data.getSaturday()));
//   branchCustomDays.forEach(data -> System.out.println("Custom Hours: " + data.getId() + " " + data.getCustomDate() + " " + data.getDailyWorkHours() + " " + data.getReason()));
   
   Map<LocalDate, CustomDays> customDaysMap = new HashMap<>();
   branchCustomDays.forEach(data -> customDaysMap.put(data.getCustomDate(), data));
   
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
         String dayValue = (!customDaysMap.containsKey(day)) ? branchWeeklyData[day.getDayOfWeek().getValue()] + "," : customDaysMap.get(day).getDailyWorkHours().equals("closed") ? "0," : "1,";
         branchMap.put(day.getMonthValue(), branchMap.get(day.getMonthValue()).append(dayValue));
         counter++;
      }
   }
   
   Map<Integer, String> branch = new HashMap<>();
   for(Integer key : branchMap.keySet()) branch.put(key, branchMap.get(key).substring(0,branchMap.get(key).length()-1));
   System.out.println("createMonthlyYearDataForBranchFINAL: " + branch);
   
   return branch;
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
   if(daysArr.length <28 || daysArr.length >31)
      throw new FormatFlagsConversionMismatchException(ErrorMessages.CONVERSION_MISMATCH_EXCEPTION.getErrorMessage(), 'c'); //character means??
   return daysArr[date.getDayOfMonth() - 1];
}

@Override
public String updateAllSingleDaysInMonthlyData(MonthlyBusinessWorkDays monthlyData, int day, int newValue) {
   if(day>7 || day<1) throw new IndexOutOfBoundsException("Day values should be between 1 and 7");
   int firstDay = (day - monthlyData.getFirstDayOfMonth().getDayOfWeek().getValue() + 7) % 7;
   String[] daysArr = monthlyData.getMonthlyData().split(",");
   while (firstDay < monthlyData.getFirstDayOfMonth().lengthOfMonth()) {
      daysArr[firstDay] = String.valueOf(newValue);
      firstDay += 7;
   }
   StringBuilder result = new StringBuilder("");
   Arrays.stream(daysArr).forEach(data -> result.append(data +","));
   return result.substring(0,result.length()-1);
}

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
}
