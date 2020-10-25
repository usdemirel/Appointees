package com.notsecure.Appointees.service;

import com.notsecure.Appointees.entity.WeeklyWorkHours;

import java.util.Optional;

public interface WeeklyWorkHoursService {
 
 Optional<WeeklyWorkHours> findById(Long id);
 Optional<WeeklyWorkHours> findByCompanyName(String name);
 
}
