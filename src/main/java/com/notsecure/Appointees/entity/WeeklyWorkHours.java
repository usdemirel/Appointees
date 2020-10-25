package com.notsecure.Appointees.entity;

import org.springframework.lang.Nullable;

import javax.persistence.*;

@Entity
public class WeeklyWorkHours {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Long id;

@OneToOne
private Company company;

@Nullable
@OneToOne
private DailyWorkHours sunday;
@Nullable
@OneToOne
private DailyWorkHours monday;
@Nullable
@OneToOne
private DailyWorkHours tuesday;
@Nullable
@OneToOne
private DailyWorkHours wednesday;
@Nullable
@OneToOne
private DailyWorkHours thursday;
@Nullable
@OneToOne
private DailyWorkHours friday;
@Nullable
@OneToOne
private DailyWorkHours saturday;

public WeeklyWorkHours() {
}

public Long getId() {
 return id;
}

public void setId(Long id) {
 this.id = id;
}

@Nullable
public DailyWorkHours getSunday() {
 return sunday;
}

public void setSunday(@Nullable DailyWorkHours sunday) {
 this.sunday = sunday;
}

@Nullable
public DailyWorkHours getMonday() {
 return monday;
}

public void setMonday(@Nullable DailyWorkHours monday) {
 this.monday = monday;
}

@Nullable
public DailyWorkHours getTuesday() {
 return tuesday;
}

public void setTuesday(@Nullable DailyWorkHours tuesday) {
 this.tuesday = tuesday;
}

@Nullable
public DailyWorkHours getWednesday() {
 return wednesday;
}

public void setWednesday(@Nullable DailyWorkHours wednesday) {
 this.wednesday = wednesday;
}

@Nullable
public DailyWorkHours getThursday() {
 return thursday;
}

public void setThursday(@Nullable DailyWorkHours thursday) {
 this.thursday = thursday;
}

@Nullable
public DailyWorkHours getFriday() {
 return friday;
}

public void setFriday(@Nullable DailyWorkHours friday) {
 this.friday = friday;
}

@Nullable
public DailyWorkHours getSaturday() {
 return saturday;
}

public void setSaturday(@Nullable DailyWorkHours saturday) {
 this.saturday = saturday;
}

@Override
public String toString() {
 return "WeeklyWorkHours{" +
                 "id=" + id +
                 ", sunday=" + sunday +
                 ", monday=" + monday +
                 ", tuesday=" + tuesday +
                 ", wednesday=" + wednesday +
                 ", thursday=" + thursday +
                 ", friday=" + friday +
                 ", saturday=" + saturday +
                 '}';
}
}
