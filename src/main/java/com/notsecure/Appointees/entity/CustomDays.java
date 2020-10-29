package com.notsecure.Appointees.entity;

import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.LocalDate;

@Entity
public class CustomDays {
@Id
private Long id;
@OneToOne
private Company company;
@OneToOne
@Nullable
private Service service; //if null, it is a custom day for the company
@OneToOne
@Nullable
private ServiceProvider serviceProvider; //if null, it is a custom day for the company
@OneToOne
@Nullable
private DailyWorkHours dailyWorkHours; // if null, OFF Day
private LocalDate date;
private String reason;

public CustomDays() {
}

public Long getId() {
 return id;
}

public void setId(Long id) {
 this.id = id;
}

@Nullable
public Service getService() {
 return service;
}

public void setService(@Nullable Service service) {
 this.service = service;
}

public Company getCompany() {
 return company;
}

public void setCompany(Company company) {
 this.company = company;
}

@Nullable
public ServiceProvider getServiceProvider() {
 return serviceProvider;
}

public void setServiceProvider(@Nullable ServiceProvider serviceProvider) {
 this.serviceProvider = serviceProvider;
}

@Nullable
public DailyWorkHours getDailyWorkHours() {
 return dailyWorkHours;
}

public void setDailyWorkHours(@Nullable DailyWorkHours dailyWorkHours) {
 this.dailyWorkHours = dailyWorkHours;
}

public LocalDate getDate() {
 return date;
}

public void setDate(LocalDate date) {
 this.date = date;
}

public String getReason() {
 return reason;
}

public void setReason(String reason) {
 this.reason = reason;
}

@Override
public String toString() {
 return "CustomDays{" +
                 "id=" + id +
                 ", company=" + company +
                 ", serviceProvider=" + serviceProvider +
                 ", dailyWorkHours=" + dailyWorkHours +
                 ", date=" + date +
                 ", reason='" + reason + '\'' +
                 '}';
}
}
