package com.notsecure.Appointees.utilityservices;

import com.notsecure.Appointees.entity.WeeklyAppointmentsPerServiceProvider;
import com.notsecure.Appointees.entity.WeeklyCustomServiceProviderSchedule;
import com.notsecure.Appointees.repository.WeeklyAppointmentsPerServiceProviderRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalTime;
import java.util.List;

@Service
public class WeeklyAppointmentsPerServiceProviderTextOperationsImpl implements WeeklyAppointmentsPerServiceProviderTextOperations{

@Autowired
WeeklyAppointmentsPerServiceProviderRepository weeklyAppointmentsPerServiceProviderRepository;


@Override
public String openSlotsForAppointmentHours(String schedule) {
   //08:00,08:30,09:00,09:30,10:00,10:30,11:00,11:30
//   StringBuilder sb = new StringBuilder("");
//   String[] times = schedule.split(",");
//   Arrays.asList(times).stream().forEach(data -> sb.append(data+";0,"));
//   return sb.deleteCharAt(sb.length()-1).toString();
   
   return schedule.replaceAll(",",";0,")+";0";
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
   result.setSunday(openSlotsForAppointmentHours(providerWeeklySchedule.getSunday()));
   result.setMonday(openSlotsForAppointmentHours(providerWeeklySchedule.getMonday()));
   result.setTuesday(openSlotsForAppointmentHours(providerWeeklySchedule.getTuesday()));
   result.setWednesday(openSlotsForAppointmentHours(providerWeeklySchedule.getWednesday()));
   result.setThursday(openSlotsForAppointmentHours(providerWeeklySchedule.getThursday()));
   result.setFriday(openSlotsForAppointmentHours(providerWeeklySchedule.getFriday()));
   result.setSaturday(openSlotsForAppointmentHours(providerWeeklySchedule.getSaturday()));
   return result;
}

@Override
public List<WeeklyAppointmentsPerServiceProvider> convertWeeklyScheduleToProviderAppointments(List<WeeklyCustomServiceProviderSchedule> providerWeeklyScheduleList) {
   return null;
}

@Override
public WeeklyAppointmentsPerServiceProvider updateProviderAppointments(WeeklyAppointmentsPerServiceProvider oldWeeklyAppointmentsPerServiceProvider, WeeklyCustomServiceProviderSchedule newProviderWeeklySchedule) {
   return null;
}
}
