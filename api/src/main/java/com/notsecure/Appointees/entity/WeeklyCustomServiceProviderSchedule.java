package com.notsecure.Appointees.entity;

import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/*
This is a calculated table
that will hold data as much as the in-advance time entered by the administration.
For example, if #ofDays entered as 90, this table will hold 90/7=12.85, which is 13-week worth of data fo the provider.
Service is a must here because a person can be assigned to different duties let's say before and after noon.
* */
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WeeklyCustomServiceProviderSchedule {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Long id;
@OneToOne
@NotNull
private Company company;
@OneToOne
@NotNull
private Branch branch;
@OneToOne
@NotNull
private Service service;
@OneToOne
@NotNull
private ServiceProvider serviceProvider;

private LocalDate firstDayOfWeek; //represents sunday
//This is an important addition here. It will allow admins to set different service time for different seasons.
private int totalServiceTime; // duration + buffer

/*
Provider#1
   totalServiceTime-A: 30min
   Service-A: "08:00,08:30,09:00,09:30,10:00,10:30,11:00,11:30"
   
   totalServiceTime-B: 15min
   Service-B: "13:00,13:15,13:30,13:45,14:00,14:15.."
   
The provider is providing two different services:
Morning each service takes 30 min in total.
Afternoon, another service takes only 15 minutes in total.
*/

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
}
