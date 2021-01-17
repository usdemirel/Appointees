package com.notsecure.Appointees.utilityservices;

import com.notsecure.Appointees.entity.Company;
import com.notsecure.Appointees.entity.ServiceProvider;
import com.notsecure.Appointees.entity.WeeklyCustomServiceProviderSchedule;
import javassist.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.Equals;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class WeeklyAppointmentsPerServiceProviderTextOperationsImplTest {
   
   @InjectMocks
   WeeklyAppointmentsPerServiceProviderTextOperationsImpl weeklyAppointmentsPerServiceProviderTextOperationsImpl;
   ServiceProvider serviceProvider = new ServiceProvider();
   WeeklyCustomServiceProviderSchedule weeklyCustomServiceProviderSchedule =
                   new WeeklyCustomServiceProviderSchedule(2500L,null,null,null,null, LocalDate.parse("2021-01-17"),30,
                                   "07:00,07:40,08:20,09:00,09:40,10:20,11:00","12:30,13:00,13:30,14:00,14:30,15:00,15:30",
                                   "12:59,13:29,13:59,14:29,14:59,15:29","12:00,12:30,13:00,13:30,14:00,14:30,15:00,15:30,16:00,16:30,17:00,17:30,18:00,18:30",
                                   "08:00,08:30,09:00,09:30,10:00,10:30,11:00,11:30,13:00,13:30,14:00,14:30,15:00,15:30,16:00,16:30,17:00,17:30","",
                                   "08:00,08:30,09:00,09:30,10:00,10:30,11:00,11:30");

@BeforeEach
void setUp() {
   MockitoAnnotations.initMocks(this);
}

@Test
void openSlotsForAppointmentHours() {
}

@Test
void convertWeeklyScheduleToProviderAppointments() throws NotFoundException {
assertEquals("12:00;0,12:30;0,13:00;0,13:30;0,14:00;0,14:30;0,15:00;0,15:30;0,16:00;0,16:30;0,17:00;0,17:30;0,18:00;0,18:30;0",
                weeklyAppointmentsPerServiceProviderTextOperationsImpl.
                                convertWeeklyScheduleToProviderAppointments(weeklyCustomServiceProviderSchedule).getWednesday());
}

@Test
void testConvertWeeklyScheduleToProviderAppointments() {
}

@Test
void updateProviderAppointments() {
}
}