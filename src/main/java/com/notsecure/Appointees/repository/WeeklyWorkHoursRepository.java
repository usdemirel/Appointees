package com.notsecure.Appointees.repository;

import com.notsecure.Appointees.entity.WeeklyWorkHours;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface WeeklyWorkHoursRepository extends CrudRepository<WeeklyWorkHours, Long> {
//Optional<WeeklyWorkHours> findByCompany_Name(String name);
// This one gets default weekly service hours for the company
Optional<WeeklyWorkHours> findWeeklyWorkHoursByCompany_NameAndServiceIsNullAndServiceProviderIsNull(String name);
// This one gets default weekly service hours. If the result is empty, then, we should take the company's weekly hours by default.
Optional<WeeklyWorkHours> findWeeklyWorkHoursByCompany_NameAndService_IdAndServiceProviderIsNull(String name, Long serviceId);
// This one gets all service providers' work hours
List<WeeklyWorkHours> findWeeklyWorkHoursByCompany_NameAndService_IdAndServiceProviderIsNotNull(String name, Long serviceId);
// This one gets a specific service provider's default weekly hours for a specific service.
List<WeeklyWorkHours> findWeeklyWorkHoursByCompany_NameAndService_IdAndServiceProvider_Email(String name, Long serviceId, String email);
// This one gets a specific service provider's default weekly hours for all registered services.
List<WeeklyWorkHours> findWeeklyWorkHoursByCompany_NameAndServiceIsNotNullAndServiceProvider_Email(String name, String email);

}
