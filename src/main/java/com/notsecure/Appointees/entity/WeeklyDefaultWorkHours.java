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
@ToString
@EqualsAndHashCode
public class WeeklyDefaultWorkHours implements Serializable {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Long id;
LocalDate effectiveBy = LocalDate.now(); // the date entered is inclusive
@OneToOne(fetch = FetchType.LAZY)
private Company company;
@OneToOne(fetch = FetchType.LAZY)
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
