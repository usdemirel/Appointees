package com.notsecure.Appointees.service;

import com.notsecure.Appointees.entity.MonthlyBusinessWorkDays;
import com.notsecure.Appointees.repository.MonthlyBusinessWorkDaysRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MonthlyBusinessWorkDaysServiceImpl implements MonthlyBusinessWorkDaysService {

@Autowired
MonthlyBusinessWorkDaysRepository monthlyBusinessWorkDaysRepository;

@Override
public Optional<MonthlyBusinessWorkDays> findMonthlyBusinessWorkDaysById(Long monthlyBusinessWorkDaysId) {
   return monthlyBusinessWorkDaysRepository.findById(monthlyBusinessWorkDaysId);
}

@Override
public Optional<MonthlyBusinessWorkDays> findMonthlyBusinessWorkDaysByBranchIdAndFirstDayOfMonth(Long branchId, LocalDate firstDayOfMonth) {
   return monthlyBusinessWorkDaysRepository.findMonthlyBusinessWorkDaysByBranchIdAndFirstDayOfMonth(branchId, firstDayOfMonth);
}

@Override
public List<MonthlyBusinessWorkDays> findMonthlyBusinessWorkDaysByCompanyId(Long companyId) {
   return monthlyBusinessWorkDaysRepository.findMonthlyBusinessWorkDaysByCompanyId(companyId);
}

@Override
public List<MonthlyBusinessWorkDays> findMonthlyBusinessWorkDaysByBranchId(Long branchId) {
   return monthlyBusinessWorkDaysRepository.findMonthlyBusinessWorkDaysByBranchId(branchId);
}
}
