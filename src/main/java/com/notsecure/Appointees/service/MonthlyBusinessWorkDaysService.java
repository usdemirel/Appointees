package com.notsecure.Appointees.service;

import com.notsecure.Appointees.entity.MonthlyBusinessWorkDays;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MonthlyBusinessWorkDaysService {
Optional<MonthlyBusinessWorkDays> findMonthlyBusinessWorkDaysById(Long monthlyBusinessWorkDaysId);
Optional<MonthlyBusinessWorkDays> findMonthlyBusinessWorkDaysByBranchIdAndFirstDayOfMonth(Long branchId, LocalDate firstDayOfMonth);
List<MonthlyBusinessWorkDays> findMonthlyBusinessWorkDaysByCompanyId(Long companyId);
List<MonthlyBusinessWorkDays> findMonthlyBusinessWorkDaysByBranchId(Long branchId);
}
