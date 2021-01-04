package com.notsecure.Appointees.utilityservices;

import com.notsecure.Appointees.entity.CustomDays;
import com.notsecure.Appointees.entity.MonthlyBusinessWorkDays;
import com.notsecure.Appointees.entity.WeeklyDefaultWorkHours;
import javassist.NotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;


/*
 * The generated table include calculated month data based on the weeklyDefaultWorkHours and customDays.
 * When there is a change in the mentioned tables for a company or branch, the change will be reflected on he table immediately.
 * When there's no change, no additional calculation will be performed.
 *
 * if branch is null, then, monthly data is valid for all branches.
 * if there is a branch data available, it overrides the company data.
 */
public interface MonthlyBusinessWorkDaysOperations {
public MonthlyBusinessWorkDays updateADayInMonthlyData(MonthlyBusinessWorkDays oldMonthlyData, LocalDate dateToBeChanged, int newValue);

public String retrieveADay(MonthlyBusinessWorkDays monthlyData, LocalDate date);

public String updateAllSingleDaysInMonthlyData(MonthlyBusinessWorkDays monthlyData, int day, int newValue); // For example, the business determines to be open all sundays, the input becomes 0,1

public Map<Integer, String> createMonthlyYearDataForBranchFINAL(Long companyId, Long branchId, int year, int initMonth, int endMonth);
}
