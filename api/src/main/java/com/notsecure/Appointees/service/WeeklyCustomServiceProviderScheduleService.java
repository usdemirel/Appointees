package com.notsecure.Appointees.service;

import com.notsecure.Appointees.entity.WeeklyCustomServiceProviderSchedule;
import java.time.LocalDate;

public interface WeeklyCustomServiceProviderScheduleService {

WeeklyCustomServiceProviderSchedule save(WeeklyCustomServiceProviderSchedule weeklyCustomServiceProviderSchedule);
Iterable<WeeklyCustomServiceProviderSchedule> generateAndSaveWeeklyCustomServiceProviderSchedule(LocalDate firstDay, Long serviceProviderId, Long companyId, Long branchId, int numOfWeeks);


}
