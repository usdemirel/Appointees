package com.notsecure.Appointees.service;

import com.notsecure.Appointees.entity.WeeklyWorkHours;
import com.notsecure.Appointees.repository.WeeklyWorkHoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WeeklyWorkHoursServiceImpl implements WeeklyWorkHoursService{
 
 @Autowired
 WeeklyWorkHoursRepository weeklyWorkHoursRepository;
 
@Override
public Optional<WeeklyWorkHours> findById(Long id) {
 return weeklyWorkHoursRepository.findById(id);
}

@Override
public Optional<WeeklyWorkHours> findByCompanyName(String name) {
 return weeklyWorkHoursRepository.findByCompany_Name(name);
}
}
