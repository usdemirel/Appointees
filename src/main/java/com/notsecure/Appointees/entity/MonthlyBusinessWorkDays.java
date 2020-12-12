package com.notsecure.Appointees.entity;

import com.sun.istack.NotNull;
import com.sun.istack.Nullable;

import javax.persistence.*;
import java.time.LocalDateTime;

/*
The generated table include calculated month data based on the weeklyDefaultWorkHours and customDays.
When there is a change in the mentioned tables for a company or branch, the change will be reflected on he table immediately.
When there's no change, no additional calculation will be performed.
 */
@Entity
public class MonthlyBusinessWorkDays {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Long id;
private LocalDateTime firstDayOfMonth;
private String monthlyData; // "0,1,1,1,1,1,1,0,0,1,..." 1 is a work day while 0 represents an OFF day.
@OneToOne
@NotNull
private Company company;

//if branch is null, then, monthly data is valid for all branches.
// if there is a branch data available, it overrides the company data.
@Nullable
@OneToOne
private Branch branch;
}
