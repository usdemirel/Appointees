package com.notsecure.Appointees.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class WeeklyDefaultWorkHours {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Long id;
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
