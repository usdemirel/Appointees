package com.notsecure.Appointees.repository;

import com.notsecure.Appointees.entity.MonthlyBusinessWorkDays;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MonthlyBusinessWorkDaysRepository extends CrudRepository<MonthlyBusinessWorkDays, Long> {

//Optional<MonthlyBusinessWorkDays> findMonthlyBusinessWorkDaysById(Long monthlyBusinessWorkDaysId);
Optional<MonthlyBusinessWorkDays> findMonthlyBusinessWorkDaysByBranchIdAndFirstDayOfMonth(Long branchId, LocalDate firstDayOfMonth);
List<MonthlyBusinessWorkDays> findMonthlyBusinessWorkDaysByCompanyId(Long companyId);
List<MonthlyBusinessWorkDays> findMonthlyBusinessWorkDaysByBranchId(Long branchId);
List<MonthlyBusinessWorkDays> findMonthlyBusinessWorkDaysByBranchIsNullAndCompanyIdAndFirstDayOfMonthIsBetweenOrderByFirstDayOfMonth(Long companyId, LocalDate initDate, LocalDate endDate);

}
