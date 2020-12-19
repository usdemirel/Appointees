package com.notsecure.Appointees.service;

import com.notsecure.Appointees.entity.WeeklyDefaultWorkHours;
import com.notsecure.Appointees.model.OutputWorkDay;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface WeeklyDefaultWorkHoursService {

Optional<WeeklyDefaultWorkHours> findById(Long id);

List<WeeklyDefaultWorkHours> findWeeklyDefaultWorkHoursByServiceIsNullAndServiceProviderIsNullAndCompanyId(Long companyId);

List<WeeklyDefaultWorkHours> findWeeklyDefaultWorkHoursByServiceIsNullAndServiceProviderIsNullAndCompanyIdAndEffectiveByIsAfterOrderByEffectiveBy(Long companyId, LocalDate lastDayOfMonth);

List<WeeklyDefaultWorkHours> findWeeklyDefaultWorkHoursByEffectiveByIsAfterAndCompanyIdAndBranchIsNullOrBranchIdOrderByEffectiveBy(LocalDate firstDay, Long companyId, Long branchId);

WeeklyDefaultWorkHours save(WeeklyDefaultWorkHours s);

Iterable<WeeklyDefaultWorkHours> saveAll(Iterable<WeeklyDefaultWorkHours> iterable);

boolean existsById(Long weeklyDefaultWorkHoursId);

Iterable<WeeklyDefaultWorkHours> findAll();

}
