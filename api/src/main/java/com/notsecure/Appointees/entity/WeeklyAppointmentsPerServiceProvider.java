package com.notsecure.Appointees.entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class WeeklyAppointmentsPerServiceProvider {
@Id
private Long id;
@OneToOne
@NotNull
private ServiceProvider serviceProvider;
private LocalDateTime firstDay; //represents sunday

/*
Example: "08:00,09:00,09:30,11:00,13:00,13:15,13:30,13:45.."
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
