package com.notsecure.Appointees.repository;

import com.notsecure.Appointees.entity.CustomDays;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CustomDaysRepository extends CrudRepository<CustomDays, Long> {
  Optional<CustomDays> findByCompany_NameAndAndServiceProviderIsNullAndDate(String name, LocalDate date);
  List<CustomDays> findByCompany_NameAndAndServiceProviderIsNullAndDateIsBetweenOrderByDate(String name, LocalDate in, LocalDate out);
  
}
