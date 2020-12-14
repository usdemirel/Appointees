package com.notsecure.Appointees.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class WeeklyDefaultWorkHours {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Long id;
LocalDate effectiveBy = LocalDate.now(); // the date entered is exclusive
@OneToOne
private Company company;
@OneToOne
private Branch branch;
@OneToOne
private ServiceProvider serviceProvider;
@OneToOne
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


}
