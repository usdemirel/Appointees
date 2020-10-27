package com.notsecure.Appointees.model;

import com.notsecure.Appointees.entity.DailyWorkHours;

import java.time.LocalDate;

public class OutputWorkDay {
  LocalDate date;
  DailyWorkHours dailyWorkHours;
  String Day;
  int DayValue;
  
  public OutputWorkDay(LocalDate date){
    this.date = date;
    this.Day = date.getDayOfWeek().name();
    this.DayValue = date.getDayOfWeek().getValue();
  }

public OutputWorkDay(LocalDate date, DailyWorkHours dailyWorkHours, String day, int dayValue) {
  this.date = date;
  this.dailyWorkHours = dailyWorkHours;
  Day = day;
  DayValue = dayValue;
}

public OutputWorkDay() {
}

@Override
public String toString() {
  return "OutputWorkDay{" +
                  "date=" + date +
                  ", dailyWorkHours=" + dailyWorkHours +
                  ", Day='" + Day + '\'' +
                  ", DayValue='" + DayValue + '\'' +
                  '}';
}

public LocalDate getDate() {
  return date;
}

public void setDate(LocalDate date) {
  this.date = date;
}

public DailyWorkHours getDailyWorkHours() {
  return dailyWorkHours;
}

public void setDailyWorkHours(DailyWorkHours dailyWorkHours) {
  this.dailyWorkHours = dailyWorkHours;
}

public String getDay() {
  return Day;
}

public void setDay(String day) {
  Day = day;
}

public int getDayValue() {
  return DayValue;
}

public void setDayValue(int dayValue) {
  DayValue = dayValue;
}
}
