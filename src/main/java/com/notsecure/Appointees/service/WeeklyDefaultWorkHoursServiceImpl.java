package com.notsecure.Appointees.service;

import com.notsecure.Appointees.entity.WeeklyDefaultWorkHours;
import com.notsecure.Appointees.model.OutputWorkDay;
import com.notsecure.Appointees.repository.CustomDaysRepository;
import com.notsecure.Appointees.repository.WeeklyDefaultWorkHoursRepository;
import com.notsecure.Appointees.utilityservices.TextOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class WeeklyDefaultWorkHoursServiceImpl implements WeeklyDefaultWorkHoursService {

@Autowired
WeeklyDefaultWorkHoursRepository weeklyDefaultWorkHoursRepository;
@Autowired
CustomDaysRepository customDaysRepository;
@Autowired
TextOperations textOperations;

@Override
public Optional<WeeklyDefaultWorkHours> findById(Long id) {
   return weeklyDefaultWorkHoursRepository.findById(id);
}

@Override
public List<WeeklyDefaultWorkHours> findWeeklyDefaultWorkHoursByServiceIsNullAndServiceProviderIsNullAndCompanyId(Long companyId) {
   return weeklyDefaultWorkHoursRepository.findWeeklyDefaultWorkHoursByServiceIsNullAndServiceProviderIsNullAndCompanyId(companyId);
}

@Override
public List<WeeklyDefaultWorkHours> findWeeklyDefaultWorkHoursByServiceIsNullAndServiceProviderIsNullAndCompanyIdAndEffectiveByIsAfterOrderByEffectiveBy(Long companyId, LocalDate lastDayOfMonth) {
   return weeklyDefaultWorkHoursRepository.findWeeklyDefaultWorkHoursByServiceIsNullAndServiceProviderIsNullAndCompanyIdAndEffectiveByIsAfterOrderByEffectiveBy(companyId, lastDayOfMonth.minusDays(1));
}

@Override
public List<WeeklyDefaultWorkHours> findWeeklyDefaultWorkHoursByEffectiveByIsAfterAndCompanyIdAndBranchIsNullOrBranchIdOrderByEffectiveBy(LocalDate firstDay, Long companyId, Long branchId) {
   return weeklyDefaultWorkHoursRepository.findWeeklyDefaultWorkHoursByEffectiveByIsAfterAndCompanyIdAndBranchIsNullOrBranchIdOrderByEffectiveBy(firstDay, companyId, branchId);
}

@Override
public WeeklyDefaultWorkHours save(WeeklyDefaultWorkHours weeklyDefaultWorkHours) {
   return weeklyDefaultWorkHoursRepository.save(weeklyDefaultWorkHours);
}

@Override
public Iterable<WeeklyDefaultWorkHours> saveAll(Iterable<WeeklyDefaultWorkHours> iterable) {
   return weeklyDefaultWorkHoursRepository.saveAll(iterable);
}

@Override
public boolean existsById(Long weeklyDefaultWorkHoursId) {
   return weeklyDefaultWorkHoursRepository.existsById(weeklyDefaultWorkHoursId);
}

@Override
public Iterable<WeeklyDefaultWorkHours> findAll() {
   return weeklyDefaultWorkHoursRepository.findAll();
}

}
