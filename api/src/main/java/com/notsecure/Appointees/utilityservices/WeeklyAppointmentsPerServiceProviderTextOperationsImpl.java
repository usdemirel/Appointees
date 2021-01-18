package com.notsecure.Appointees.utilityservices;

import com.notsecure.Appointees.entity.WeeklyAppointmentsPerServiceProvider;
import com.notsecure.Appointees.entity.WeeklyCustomServiceProviderSchedule;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;
import javax.management.InstanceAlreadyExistsException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class WeeklyAppointmentsPerServiceProviderTextOperationsImpl implements WeeklyAppointmentsPerServiceProviderTextOperations{

@Override
public String openSlotsForAppointmentHours(String schedule) {
   //08:00,08:30,09:00,09:30,10:00,10:30,11:00,11:30
   return schedule.replaceAll(",",";0,")+";0";
}

private boolean isAnyAppointmentTakenOnAnAppointedDay(String schedule) {
   //12:00;0,12:30;0,13:00;0,13:30;0,14:00;0,14:30;0,15:00;0,15:30;0,16:00;0,16:30;0,17:00;0,17:30;0,18:00;0,18:30;0
   return schedule.contains(";1");
}

private boolean isAnyAppointmentTakenThroughoutWeek(WeeklyAppointmentsPerServiceProvider weeklyAppointmentsPerServiceProvider) {
   //12:00;0,12:30;0,13:00;0,13:30;0,14:00;0,14:30;0,15:00;0,15:30;0,16:00;0,16:30;0,17:00;0,17:30;0,18:00;0,18:30;0
   StringBuilder sb = new StringBuilder("");
   sb.append(weeklyAppointmentsPerServiceProvider.getSunday()+",").append(weeklyAppointmentsPerServiceProvider.getMonday()+",").
                   append(weeklyAppointmentsPerServiceProvider.getTuesday()+",").append(weeklyAppointmentsPerServiceProvider.getWednesday()+",").
                   append(weeklyAppointmentsPerServiceProvider.getThursday()+",").append(weeklyAppointmentsPerServiceProvider.getFriday()+",").
                   append(weeklyAppointmentsPerServiceProvider.getSaturday());
   return sb.indexOf(";1")!=-1;
}

private String changeTimeSlotOnAnAppointedDay(String appointment, LocalTime time, String newValue) throws NotFoundException {
   int start = appointment.indexOf(time.toString())+6;
   if(start==5) throw new NotFoundException("Time spot is not found!");
   StringBuilder sb = new StringBuilder(appointment);
   sb.replace(start, start+1, newValue);
   return sb.toString();
}

@Override
public WeeklyAppointmentsPerServiceProvider convertWeeklyScheduleToProviderAppointments(WeeklyCustomServiceProviderSchedule providerWeeklySchedule) throws NotFoundException {
   
   WeeklyAppointmentsPerServiceProvider result = new WeeklyAppointmentsPerServiceProvider(null,providerWeeklySchedule.getCompany(),providerWeeklySchedule.getBranch(),
                   providerWeeklySchedule.getService(),providerWeeklySchedule.getServiceProvider(),providerWeeklySchedule.getFirstDayOfWeek(),
                   "","","","","","","");
   assignDailySchedules(result, providerWeeklySchedule);
   return result;
}

private WeeklyAppointmentsPerServiceProvider assignDailySchedules(WeeklyAppointmentsPerServiceProvider input, WeeklyCustomServiceProviderSchedule providerWeeklySchedule){
   input.setSunday(openSlotsForAppointmentHours(providerWeeklySchedule.getSunday()));
   input.setMonday(openSlotsForAppointmentHours(providerWeeklySchedule.getMonday()));
   input.setTuesday(openSlotsForAppointmentHours(providerWeeklySchedule.getTuesday()));
   input.setWednesday(openSlotsForAppointmentHours(providerWeeklySchedule.getWednesday()));
   input.setThursday(openSlotsForAppointmentHours(providerWeeklySchedule.getThursday()));
   input.setFriday(openSlotsForAppointmentHours(providerWeeklySchedule.getFriday()));
   input.setSaturday(openSlotsForAppointmentHours(providerWeeklySchedule.getSaturday()));
   return input;
}

@Override
public List<WeeklyAppointmentsPerServiceProvider> convertWeeklyScheduleToProviderAppointments(List<WeeklyCustomServiceProviderSchedule> providerWeeklyScheduleList) throws NotFoundException {
   List<WeeklyAppointmentsPerServiceProvider> result = new ArrayList<>();
   for(WeeklyCustomServiceProviderSchedule weeklyCustomServiceProviderSchedule :providerWeeklyScheduleList)
      result.add(convertWeeklyScheduleToProviderAppointments(weeklyCustomServiceProviderSchedule));
   return result;
}

@Override
public WeeklyAppointmentsPerServiceProvider updateProviderAppointments(WeeklyAppointmentsPerServiceProvider oldWeeklyAppointmentsPerServiceProvider, WeeklyCustomServiceProviderSchedule newProviderWeeklySchedule) throws InstanceAlreadyExistsException {
   if(isAnyAppointmentTakenThroughoutWeek(oldWeeklyAppointmentsPerServiceProvider))
      throw new InstanceAlreadyExistsException("Appointment is already taken. Please remove or reschedule the appointments for a different week and try again!");
   return assignDailySchedules(oldWeeklyAppointmentsPerServiceProvider,newProviderWeeklySchedule);
}
}
