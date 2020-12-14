package com.notsecure.Appointees.service;

import com.notsecure.Appointees.entity.CustomDays;
import com.notsecure.Appointees.repository.CustomDaysRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CustomDaysServiceImpl implements CustomDaysService{
   
   @Autowired
CustomDaysRepository customDaysRepository;


@Override
public List<CustomDays> findByCompanyIdAndServiceProviderIsNullAndCustomDateIsBetweenOrderByCustomDate(Long companyId, LocalDate in, LocalDate out) {
   return customDaysRepository.findByCompanyIdAndServiceProviderIsNullAndCustomDateIsBetweenOrderByCustomDate(companyId, in, out);
}

@Override
public List<CustomDays> findByCompanyIdAndServiceProviderIsNullAndCustomDateIsBetweenAndBranchIdOrBranchIsNullOrderByCustomDate(Long companyId, LocalDate in, LocalDate out, Long branchId) {
   return customDaysRepository.findByCompanyIdAndServiceProviderIsNullAndCustomDateIsBetweenAndBranchIdOrBranchIsNullOrderByCustomDate(companyId, in, out, branchId);
}
}
