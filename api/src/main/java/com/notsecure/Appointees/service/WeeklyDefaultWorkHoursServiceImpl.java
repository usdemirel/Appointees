package com.notsecure.Appointees.service;

import com.notsecure.Appointees.entity.WeeklyDefaultWorkHours;
import com.notsecure.Appointees.model.ErrorMessages;
import com.notsecure.Appointees.repository.CustomDaysRepository;
import com.notsecure.Appointees.repository.WeeklyDefaultWorkHoursRepository;
import com.notsecure.Appointees.utilityservices.MonthlyBusinessWorkDaysOperations;
import com.notsecure.Appointees.utilityservices.TextOperations;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.*;

@Service
public class WeeklyDefaultWorkHoursServiceImpl implements WeeklyDefaultWorkHoursService {

@Autowired
WeeklyDefaultWorkHoursRepository weeklyDefaultWorkHoursRepository;
@Autowired
MonthlyBusinessWorkDaysService monthlyBusinessWorkDaysService;
@Autowired
MonthlyBusinessWorkDaysOperations monthlyBusinessWorkDaysOperations;
@Autowired
CustomDaysRepository customDaysRepository;
@Autowired
TextOperations textOperations;
@Autowired
WeeklyCustomServiceProviderScheduleService weeklyCustomServiceProviderScheduleService;

@Override
public Optional<WeeklyDefaultWorkHours> findById(Long id) throws NotFoundException {
   Optional<WeeklyDefaultWorkHours> weeklyDefaultWorkHours = weeklyDefaultWorkHoursRepository.findById(id);
   if (weeklyDefaultWorkHours.isEmpty()) throw new NotFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
   return weeklyDefaultWorkHours;
}

@Override
public List<WeeklyDefaultWorkHours> findWeeklyDefaultWorkHoursByServiceIsNullAndServiceProviderIsNullAndCompanyId(Long companyId) {
   return weeklyDefaultWorkHoursRepository.findWeeklyDefaultWorkHoursByServiceIsNullAndServiceProviderIsNullAndCompanyId(companyId);
}

@Override
public List<WeeklyDefaultWorkHours> findWeeklyDefaultWorkHoursByServiceIsNullAndServiceProviderIsNullAndCompanyIdAndEffectiveByIsAfterOrderByEffectiveBy(Long companyId, LocalDate lastDayOfMonth) {
   return weeklyDefaultWorkHoursRepository.findWeeklyDefaultWorkHoursByServiceIsNullAndServiceProviderIsNullAndCompanyIdAndEffectiveByIsAfterOrderByEffectiveBy(companyId, lastDayOfMonth.minusDays(1));
}

@Override
public List<WeeklyDefaultWorkHours> findWeeklyDefaultWorkHoursByEffectiveByIsAfterAndCompanyIdAndBranchIsNullOrBranchIdOrderByEffectiveBy(LocalDate firstDay, Long companyId, Long branchId) {
   return weeklyDefaultWorkHoursRepository.findWeeklyDefaultWorkHoursByEffectiveByIsAfterAndCompanyIdAndBranchIsNullOrBranchIdOrderByEffectiveBy(firstDay.minusDays(1), companyId, branchId);
}

@Override
public List<WeeklyDefaultWorkHours> findWeeklyDefaultWorkHoursByServiceIsNullAndEffectiveByIsAfterAndCompanyIdAndBranchIsNullOrBranchIdOrderByEffectiveBy(LocalDate firstDay, Long companyId, Long branchId) {
   return weeklyDefaultWorkHoursRepository.findWeeklyDefaultWorkHoursByServiceIsNullAndEffectiveByIsAfterAndCompanyIdAndBranchIsNullOrBranchIdOrderByEffectiveBy(firstDay.minusDays(1), companyId, branchId);
}

@Override
@Transactional
public WeeklyDefaultWorkHours save(WeeklyDefaultWorkHours weeklyDefaultWorkHours) throws Exception {
   System.out.println(" 1 ");
   //Checks for duplication
   if(weeklyDefaultWorkHours.getId() == null && weeklyDefaultWorkHoursRepository.findWeeklyDefaultWorkHoursByEffectiveByAndCompanyIdAndBranchIdAndServiceIdAndServiceProviderId(
                   weeklyDefaultWorkHours.getEffectiveBy().minusDays(1),weeklyDefaultWorkHours.getCompany().getId(),
                   (weeklyDefaultWorkHours.getBranch()!=null)?weeklyDefaultWorkHours.getBranch().getId():null,
                   (weeklyDefaultWorkHours.getService()!=null)?weeklyDefaultWorkHours.getService().getId():null,
                   (weeklyDefaultWorkHours.getServiceProvider()!=null)?weeklyDefaultWorkHours.getServiceProvider().getId():null).isPresent()) {
      throw new DuplicateKeyException("Duplicate Effective By Date already exists");
   }
   System.out.println(" 2 ");
   
   //Finds the latest entry and changes its effectiveBy date accordingly
   Optional<WeeklyDefaultWorkHours> former = weeklyDefaultWorkHoursRepository.findWeeklyDefaultWorkHoursByEffectiveByAndCompanyIdAndBranchIdAndServiceIdAndServiceProviderId(
                   LocalDate.parse("2099-12-31"),weeklyDefaultWorkHours.getCompany().getId(),
                   (weeklyDefaultWorkHours.getBranch()!=null)?weeklyDefaultWorkHours.getBranch().getId():null,
                   (weeklyDefaultWorkHours.getService()!=null)?weeklyDefaultWorkHours.getService().getId():null,
                   (weeklyDefaultWorkHours.getServiceProvider()!=null)?weeklyDefaultWorkHours.getServiceProvider().getId():null);
   if(former.isPresent()) {
      former.get().setEffectiveBy(weeklyDefaultWorkHours.getEffectiveBy().minusDays(1));
      weeklyDefaultWorkHoursRepository.save(former.get());
   }
   System.out.println(" 3 ");
   
   //attempts to save the weekly default work hours.
   weeklyDefaultWorkHours.setEffectiveBy(LocalDate.parse("2099-12-31"));
   WeeklyDefaultWorkHours workHours = weeklyDefaultWorkHoursRepository.save(weeklyDefaultWorkHours);
   if (workHours == null) throw new Exception("WeeklyDefaultWorkHours is not saved");
   
   if (weeklyDefaultWorkHours.getBranch() != null && weeklyDefaultWorkHours.getService() == null && weeklyDefaultWorkHours.getServiceProvider() == null){
      //Based on the update, monthly data is generated here
      monthlyBusinessWorkDaysOperations.saveMonthlyData(weeklyDefaultWorkHours);
   }else if (weeklyDefaultWorkHours.getBranch() != null && weeklyDefaultWorkHours.getService() != null && weeklyDefaultWorkHours.getServiceProvider() != null) {
      // This case covers the service provider schedule generation.
   weeklyCustomServiceProviderScheduleService.generateAndSaveWeeklyCustomServiceProviderSchedule(LocalDate.now().minusDays(LocalDate.now().getDayOfWeek().getValue() % 7), weeklyDefaultWorkHours.getServiceProvider().getId(),
                   weeklyDefaultWorkHours.getCompany().getId(), weeklyDefaultWorkHours.getBranch().getId(), 2); // this value should be retrieved from service
}


//   Map<Integer, String> monthlyYearDataMap = null;
//   if (weeklyDefaultWorkHours.getBranch() != null && weeklyDefaultWorkHours.getService() == null && weeklyDefaultWorkHours.getServiceProvider() == null) {
//      int year = LocalDate.now().getYear();
//      List<MonthlyBusinessWorkDays> monthlyBusinessWorkDaysList = monthlyBusinessWorkDaysService.
//                      findMonthlyBusinessWorkDaysByBranchIdAndFirstDayOfMonthIsBetweenOrderByFirstDayOfMonth(weeklyDefaultWorkHours.getBranch().getId(),
//                                      LocalDate.of(year, LocalDate.now().getMonthValue(), 1), LocalDate.of(year, 12, 1));
//      monthlyYearDataMap = monthlyBusinessWorkDaysOperations.createMonthlyYearDataForBranchFINAL(weeklyDefaultWorkHours.getCompany().getId(),
//                      weeklyDefaultWorkHours.getBranch() == null ? null : weeklyDefaultWorkHours.getBranch().getId(),
//                      year, 1, 12);
//
//      MonthlyBusinessWorkDays monthlyBusinessWorkDays = new MonthlyBusinessWorkDays();
//      monthlyBusinessWorkDays.setCompany(weeklyDefaultWorkHours.getCompany());
//      monthlyBusinessWorkDays.setBranch(weeklyDefaultWorkHours.getBranch());
//      List<MonthlyBusinessWorkDays> monthlyYearDataList = new ArrayList<>();
//      for (Integer key : monthlyYearDataMap.keySet()) {
//         MonthlyBusinessWorkDays aCopy = (MonthlyBusinessWorkDays) monthlyBusinessWorkDays.clone();
//         aCopy = monthlyBusinessWorkDaysList.stream().
//                         filter(data -> data.getFirstDayOfMonth().equals(LocalDate.of(year, key, 1))).findFirst().
//                         orElse(aCopy);
//         aCopy.setMonthlyData(monthlyYearDataMap.get(key));
//         aCopy.setFirstDayOfMonth(LocalDate.of(year, key, 1));
//         monthlyYearDataList.add(aCopy);
//      }
//      if (monthlyBusinessWorkDaysService.saveAll(monthlyYearDataList).iterator().hasNext() == false)
//         throw new Exception("MonthlyBusinessWorkDays is not saved");
//   } else if (weeklyDefaultWorkHours.getBranch() != null && weeklyDefaultWorkHours.getService() != null && weeklyDefaultWorkHours.getServiceProvider() != null) {
//      // This case covers the service provide schedule generation.
//      weeklyCustomServiceProviderScheduleService.generateAndSaveWeeklyCustomServiceProviderSchedule(LocalDate.now().minusDays(LocalDate.now().getDayOfWeek().getValue() % 7), weeklyDefaultWorkHours.getServiceProvider().getId(),
//                      weeklyDefaultWorkHours.getCompany().getId(), weeklyDefaultWorkHours.getBranch().getId(), 2); // this value should be retrieved from service
//   }
   return workHours;
}

@Override
public Iterable<WeeklyDefaultWorkHours> saveAll(Iterable<WeeklyDefaultWorkHours> iterable) {
   return weeklyDefaultWorkHoursRepository.saveAll(iterable);
}

@Override
public boolean existsById(Long weeklyDefaultWorkHoursId) {
   return weeklyDefaultWorkHoursRepository.existsById(weeklyDefaultWorkHoursId);
}

@Override
public Iterable<WeeklyDefaultWorkHours> findAll() {
   return weeklyDefaultWorkHoursRepository.findAll();
}

@Override
public List<WeeklyDefaultWorkHours> findWeeklyDefaultWorkHoursByServiceIdAndServiceProviderIdAndEffectiveByIsAfterOrderByEffectiveBy(Long serviceId, Long serviceProviderId, LocalDate date) {
   return weeklyDefaultWorkHoursRepository.findWeeklyDefaultWorkHoursByServiceIdAndServiceProviderIdAndEffectiveByIsAfterOrderByEffectiveBy(serviceId, serviceProviderId, date);
}

@Override
public List<WeeklyDefaultWorkHours> findWeeklyDefaultWorkHoursByServiceProviderIdAndCompanyIdAndEffectiveByIsAfterOrderByEffectiveBy(Long serviceProviderId, Long companyId, LocalDate date) {
   return weeklyDefaultWorkHoursRepository.findWeeklyDefaultWorkHoursByServiceProviderIdAndCompanyIdAndEffectiveByIsAfterOrderByEffectiveBy(serviceProviderId, companyId, date);
}

}
