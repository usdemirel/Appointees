package com.notsecure.Appointees.repository;

import com.notsecure.Appointees.entity.CustomDays;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CustomDaysRepository extends CrudRepository<CustomDays, Long> {
//  Optional<CustomDays> findByCompany_NameAndAndServiceProviderIsNullAndDate(String name, LocalDate date);
List<CustomDays> findByCompanyIdAndServiceProviderIsNullAndCustomDateIsBetweenOrderByCustomDate(Long companyId, LocalDate in, LocalDate out);
List<CustomDays> findByCompanyIdAndServiceProviderIsNullAndCustomDateIsBetweenAndBranchIdOrBranchIsNullOrderByCustomDate(Long companyId, LocalDate in, LocalDate out, Long branchId);
List<CustomDays> findByServiceProviderIsNullAndBranchIsNullAndCompanyIdAndCustomDateIsBetweenOrderByCustomDate(Long companyId, LocalDate initDate, LocalDate endDate);
List<CustomDays> findByServiceProviderIsNullAndCompanyIdAndBranchIdAndCustomDateIsBetweenOrderByCustomDate(Long companyId, Long branchId, LocalDate initDate, LocalDate endDate);
List<CustomDays> findByBranchIdAndServiceProviderIdAndCustomDateIsBetweenOrderByCustomDate(Long branchId, Long serviceProviderId, LocalDate initDate, LocalDate endDate);
}
