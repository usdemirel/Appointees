package com.notsecure.Appointees.utilityservices;

import com.notsecure.Appointees.entity.WeeklyAppointmentsPerServiceProvider;
import com.notsecure.Appointees.entity.WeeklyCustomServiceProviderSchedule;
import javassist.NotFoundException;

import javax.management.InstanceAlreadyExistsException;
import java.util.List;

public interface WeeklyAppointmentsPerServiceProviderTextOperations {
   
   String openSlotsForAppointmentHours(String schedule);
   WeeklyAppointmentsPerServiceProvider convertWeeklyScheduleToProviderAppointments(WeeklyCustomServiceProviderSchedule providerWeeklySchedule) throws NotFoundException;
   List<WeeklyAppointmentsPerServiceProvider> convertWeeklyScheduleToProviderAppointments(List<WeeklyCustomServiceProviderSchedule> providerWeeklyScheduleList) throws NotFoundException;
   WeeklyAppointmentsPerServiceProvider updateProviderAppointments(WeeklyAppointmentsPerServiceProvider oldWeeklyAppointmentsPerServiceProvider, WeeklyCustomServiceProviderSchedule newProviderWeeklySchedule) throws InstanceAlreadyExistsException;
   
   
   
}
