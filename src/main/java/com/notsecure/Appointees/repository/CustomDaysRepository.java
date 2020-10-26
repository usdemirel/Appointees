package com.notsecure.Appointees.repository;

import com.notsecure.Appointees.entity.CustomDays;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface CustomDaysRepository extends CrudRepository<CustomDays, Long> {
  Optional<CustomDays> findByCompany_NameAndAndServiceProviderIsNullAndDate(String name, LocalDate date);
}
