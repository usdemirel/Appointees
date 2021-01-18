package com.notsecure.Appointees.service;

import com.notsecure.Appointees.entity.WeeklyAppointmentsPerServiceProvider;
import com.notsecure.Appointees.model.ErrorMessages;
import com.notsecure.Appointees.repository.WeeklyAppointmentsPerServiceProviderRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class WeeklyAppointmentsPerServiceProviderServiceImpl implements WeeklyAppointmentsPerServiceProviderService {

@Autowired
WeeklyAppointmentsPerServiceProviderRepository weeklyAppointmentsPerServiceProviderRepository;

@Override
public Optional<WeeklyAppointmentsPerServiceProvider> findWeeklyAppointmentsPerServiceProviderById(Long id) throws NotFoundException {
   Optional<WeeklyAppointmentsPerServiceProvider> weeklyAppointmentsPerServiceProvider =
                   weeklyAppointmentsPerServiceProviderRepository.findById(id);
   if (weeklyAppointmentsPerServiceProvider.isEmpty())
      throw new NotFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
   return weeklyAppointmentsPerServiceProvider;
}

@Override
public WeeklyAppointmentsPerServiceProvider save(WeeklyAppointmentsPerServiceProvider weeklyAppointmentsPerServiceProvider) throws Exception {
   WeeklyAppointmentsPerServiceProvider saved = weeklyAppointmentsPerServiceProviderRepository.save(weeklyAppointmentsPerServiceProvider);
   if(saved == null) throw new Exception(ErrorMessages.COULD_NOT_SAVE_RECORD.getErrorMessage());
   return saved;
}
}