package com.notsecure.Appointees.service;

import com.notsecure.Appointees.entity.CustomDays;

import java.time.LocalDate;
import java.util.List;

public interface CustomDaysService {
List<CustomDays> findByCompanyIdAndServiceProviderIsNullAndCustomDateIsBetweenOrderByCustomDate(Long companyId, LocalDate in, LocalDate out);
List<CustomDays> findByCompanyIdAndServiceProviderIsNullAndCustomDateIsBetweenAndBranchIdOrBranchIsNullOrderByCustomDate(Long companyId, LocalDate in, LocalDate out, Long branchId);


}
