package com.notsecure.Appointees.service;

import com.notsecure.Appointees.entity.WeeklyDefaultWorkHours;
import com.notsecure.Appointees.model.OutputWorkDay;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface WeeklyWorkHoursService {

Optional<WeeklyDefaultWorkHours> findById(Long id);
Optional<WeeklyDefaultWorkHours> findByCompanyName(String name);
List<OutputWorkDay> getWeeklyHours(String company, String timeWindow, String dateStr);
List<OutputWorkDay> getDefaultDays(String company, int day);
List<OutputWorkDay> getServiceWorkHours(String company, long serviceId, String timeWindow, LocalDate date);
}
