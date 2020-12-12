package com.notsecure.Appointees.utilityservices;

import com.notsecure.Appointees.entity.CustomDays;
import com.notsecure.Appointees.entity.WeeklyDefaultWorkHours;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class MonthlyBusinessWordDaysOperationsImpl implements MonthlyBusinessWorkDaysOperations{
@Override
public String createMonthlyData(LocalDate firstDay, WeeklyDefaultWorkHours weeklyDefaultWorkHours, CustomDays customDays) {
   return null;
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
