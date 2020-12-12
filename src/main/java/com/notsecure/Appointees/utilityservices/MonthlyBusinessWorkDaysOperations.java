package com.notsecure.Appointees.utilityservices;

import com.notsecure.Appointees.entity.CustomDays;
import com.notsecure.Appointees.entity.WeeklyDefaultWorkHours;

import java.time.LocalDate;


/*
* The generated table include calculated month data based on the weeklyDefaultWorkHours and customDays.
* When there is a change in the mentioned tables for a company or branch, the change will be reflected on he table immediately.
* When there's no change, no additional calculation will be performed.
*
* if branch is null, then, monthly data is valid for all branches.
* if there is a branch data available, it overrides the company data.
 */
public interface MonthlyBusinessWorkDaysOperations {

public String createMonthlyData(LocalDate firstDay, WeeklyDefaultWorkHours weeklyDefaultWorkHours, CustomDays customDays);
public String updateADayInMonthlyData(String oldMonthlyData, LocalDate dateToBeChanged, int newValue);
public int retrieveADay(LocalDate date);
//Sunday:0, Monday:1,...,Saturday:6
public String updateAllSingleDaysInMonthlyData(LocalDate firstDayOfMonth, int day, int newValue); // For example, the business determines to be open all sundays, the input becomes 0,1

}
