package com.notsecure.Appointees.repository;

import com.notsecure.Appointees.entity.WeeklyWorkHours;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface WeeklyWorkHoursRepository extends CrudRepository<WeeklyWorkHours, Long> {
 Optional<WeeklyWorkHours> findByCompany_Name(String name);
}
