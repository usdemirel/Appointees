package com.notsecure.Appointees.utilityservices;

import com.notsecure.Appointees.entity.MonthlyBusinessWorkDays;
import com.notsecure.Appointees.service.CustomDaysService;
import com.notsecure.Appointees.service.MonthlyBusinessWorkDaysService;
import com.notsecure.Appointees.service.WeeklyDefaultWorkHoursService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Collections;
import java.util.FormatFlagsConversionMismatchException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class MonthlyBusinessWorkDaysOperationsImplTest {
@InjectMocks
MonthlyBusinessWorkDaysOperationsImpl monthlyBusinessWorkDaysOperations;
@Mock
WeeklyDefaultWorkHoursService weeklyDefaultWorkHoursService;
@Mock
CustomDaysService customDaysService;
@Mock
MonthlyBusinessWorkDaysService monthlyBusinessWorkDaysService;

@BeforeEach
void setUp() {
   MockitoAnnotations.initMocks(this);
}

@AfterEach
void tearDown() {
}

@Test
void createMonthlyYearDataForBranchFINAL() {
}

@Test
void updateADayInMonthlyData() {
}

@Test
void retrieveADay() {
   MonthlyBusinessWorkDays monthlyBusinessWorkDays = new MonthlyBusinessWorkDays(700L, LocalDate.parse("2020-12-01"), "1,1,1,1,0,0,1,1,1,1,1,0,0,1,1,1,1,1,0,0,1,1,1,1,1,0,0,1,1,1,1",null,null);
//   when(monthlyBusinessWorkDaysRepository.findMonthlyBusinessWorkDaysByBranchId(anyLong())).thenReturn(Collections.singletonList(monthlyBusinessWorkDays));
   String day = monthlyBusinessWorkDaysOperations.retrieveADay(monthlyBusinessWorkDays, LocalDate.parse("2020-12-05"));
   assertEquals(day, "0");
}

@Test
void retrieveADay_ConversionMismatchException() {
   MonthlyBusinessWorkDays monthlyBusinessWorkDays =
                   new MonthlyBusinessWorkDays(799L, LocalDate.parse("2020-12-01"), "1,1,1,1,0,0,1,1,1,1,1,0,0,1,1,1,1,1,0,0,1,1,1,1,1,0,0,1,1,1,1,0,0",null,null);
   assertThrows(FormatFlagsConversionMismatchException.class, ()-> {
                      monthlyBusinessWorkDaysOperations.retrieveADay(monthlyBusinessWorkDays, LocalDate.parse("2020-12-05"));
                   });
}

@Test
void updateAllSingleDaysInMonthlyData() {
}
}