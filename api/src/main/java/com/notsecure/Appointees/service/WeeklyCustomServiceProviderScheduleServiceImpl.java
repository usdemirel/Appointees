package com.notsecure.Appointees.service;

import com.notsecure.Appointees.entity.WeeklyCustomServiceProviderSchedule;
import com.notsecure.Appointees.repository.WeeklyCustomServiceProviderScheduleRepository;
import com.notsecure.Appointees.utilityservices.TextOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class WeeklyCustomServiceProviderScheduleServiceImpl implements WeeklyCustomServiceProviderScheduleService{
@Autowired
WeeklyCustomServiceProviderScheduleRepository weeklyCustomServiceProviderScheduleRepository;
@Autowired
TextOperations textOperations;


@Override
public WeeklyCustomServiceProviderSchedule save(WeeklyCustomServiceProviderSchedule weeklyCustomServiceProviderSchedule) {
   return weeklyCustomServiceProviderScheduleRepository.save(weeklyCustomServiceProviderSchedule);
}

@Override
public Iterable<WeeklyCustomServiceProviderSchedule> generateAndSaveWeeklyCustomServiceProviderSchedule(LocalDate firstDay, Long serviceProviderId, Long companyId, Long branchId, int numOfWeeks) {
   List<WeeklyCustomServiceProviderSchedule> weeklyCustomServiceProviderScheduleList =
                   textOperations.generateWeeklyCustomServiceProviderSchedule(firstDay, serviceProviderId, companyId, branchId, numOfWeeks);
   Iterable<WeeklyCustomServiceProviderSchedule> saved = weeklyCustomServiceProviderScheduleRepository.saveAll(weeklyCustomServiceProviderScheduleList);
   return saved;
}

}
