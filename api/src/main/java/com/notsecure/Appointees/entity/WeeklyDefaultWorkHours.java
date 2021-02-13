package com.notsecure.Appointees.entity;

import lombok.*;
import org.springframework.lang.Nullable;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class WeeklyDefaultWorkHours implements Serializable {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Long id;
LocalDate effectiveBy; // the date entered is inclusive
@JoinColumn(updatable = false)
@OneToOne(cascade = CascadeType.DETACH)
private Company company;
@JoinColumn(updatable = false)
@OneToOne(cascade = CascadeType.DETACH)
private Branch branch;
@JoinColumn(updatable = false)
@OneToOne(cascade = CascadeType.DETACH)
private ServiceProvider serviceProvider;
@JoinColumn(updatable = false)
@OneToOne(cascade = CascadeType.DETACH)
private Service service;
@Nullable
private String sunday;
@Nullable
private String monday;
@Nullable
private String tuesday;
@Nullable
private String wednesday;
@Nullable
private String thursday;
@Nullable
private String friday;
@Nullable
private String saturday;

public WeeklyDefaultWorkHours(WeeklyDefaultWorkHours weeklyDefaultWorkHours) {
   this.id = weeklyDefaultWorkHours.getId();
   this.effectiveBy = weeklyDefaultWorkHours.getEffectiveBy();
   this.company = weeklyDefaultWorkHours.getCompany();
   this.branch = weeklyDefaultWorkHours.getBranch();
   this.serviceProvider = weeklyDefaultWorkHours.getServiceProvider();
   this.service = weeklyDefaultWorkHours.getService();
   this.sunday = weeklyDefaultWorkHours.getSunday();
   this.monday = weeklyDefaultWorkHours.getMonday();
   this.tuesday = weeklyDefaultWorkHours.getTuesday();
   this.wednesday = weeklyDefaultWorkHours.getWednesday();
   this.thursday = weeklyDefaultWorkHours.getThursday();
   this.friday = weeklyDefaultWorkHours.getFriday();
   this.saturday = weeklyDefaultWorkHours.getSaturday();
}

public WeeklyDefaultWorkHours setEffectiveBy(LocalDate effectiveBy) {
   this.effectiveBy = effectiveBy;
   return this;
}

}
