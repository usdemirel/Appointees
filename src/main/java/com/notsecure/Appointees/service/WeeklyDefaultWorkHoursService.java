package com.notsecure.Appointees.service;

import com.notsecure.Appointees.entity.WeeklyDefaultWorkHours;
import javassist.NotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface WeeklyDefaultWorkHoursService {

Optional<WeeklyDefaultWorkHours> findById(Long id) throws NotFoundException;

List<WeeklyDefaultWorkHours> findWeeklyDefaultWorkHoursByServiceIsNullAndServiceProviderIsNullAndCompanyId(Long companyId);

List<WeeklyDefaultWorkHours> findWeeklyDefaultWorkHoursByServiceIsNullAndServiceProviderIsNullAndCompanyIdAndEffectiveByIsAfterOrderByEffectiveBy(Long companyId, LocalDate lastDayOfMonth);

List<WeeklyDefaultWorkHours> findWeeklyDefaultWorkHoursByEffectiveByIsAfterAndCompanyIdAndBranchIsNullOrBranchIdOrderByEffectiveBy(LocalDate firstDay, Long companyId, Long branchId);

List<WeeklyDefaultWorkHours> findWeeklyDefaultWorkHoursByServiceIsNullAndEffectiveByIsAfterAndCompanyIdAndBranchIsNullOrBranchIdOrderByEffectiveBy(LocalDate firstDay, Long companyId, Long branchId);

WeeklyDefaultWorkHours save(WeeklyDefaultWorkHours s) throws Exception;

Iterable<WeeklyDefaultWorkHours> saveAll(Iterable<WeeklyDefaultWorkHours> iterable);

boolean existsById(Long weeklyDefaultWorkHoursId);

Iterable<WeeklyDefaultWorkHours> findAll();

List<WeeklyDefaultWorkHours> findWeeklyDefaultWorkHoursByServiceIdAndServiceProviderIdAndEffectiveByIsAfterOrderByEffectiveBy(Long serviceId,Long serviceProviderId, LocalDate date);
List<WeeklyDefaultWorkHours> findWeeklyDefaultWorkHoursByServiceProviderIdAndCompanyIdAndEffectiveByIsAfterOrderByEffectiveBy(Long serviceProviderId, Long companyId, LocalDate date);

}
