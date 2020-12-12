package com.notsecure.Appointees.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CustomDays {
@Id
private Long id;
private LocalDateTime dateCreated;
@OneToOne
private Company company;
@OneToOne
@Nullable
private Branch branch; //if null, it is a custom day for all branches
@OneToOne
@Nullable
private ServiceProvider serviceProvider; //if null, it is a custom day for the company or branch
private String dailyWorkHours; // if null, OFF Day
private LocalDate customDate;
private String reason;


}
