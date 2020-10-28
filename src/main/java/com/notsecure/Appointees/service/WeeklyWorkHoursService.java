package com.notsecure.Appointees.service;

import com.notsecure.Appointees.entity.WeeklyWorkHours;
import com.notsecure.Appointees.model.OutputWorkDay;

import java.util.List;
import java.util.Optional;

public interface WeeklyWorkHoursService {
 
 Optional<WeeklyWorkHours> findById(Long id);
 Optional<WeeklyWorkHours> findByCompanyName(String name);
 List<OutputWorkDay> getWeeklyHours(String company, String timeWindow, String dateStr);
}
