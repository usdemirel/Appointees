package com.notsecure.Appointees.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/*
The generated table include calculated month data based on the weeklyDefaultWorkHours and customDays.
When there is a change in the mentioned tables for a company or branch, the change will be reflected on he table immediately.
When there's no change, no additional calculation will be performed.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class MonthlyBusinessWorkDays {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Long id;
private LocalDate firstDayOfMonth;
private String monthlyData; // "0,1,1,1,1,1,1,0,0,1,..." 1 is a work day while 0 represents an OFF day.
@OneToOne
@NotNull
private Company company;

//if branch is null, then, monthly data is valid for all branches.
// if there is a branch data available, it overrides the company data.
@Nullable
@OneToOne
private Branch branch;

public MonthlyBusinessWorkDays setMonthlyData(String monthlyData) {
   this.monthlyData = monthlyData;
   return this;
}
@Override
public Object clone() {
   try {
      return super.clone();
   } catch (CloneNotSupportedException e) {
      return new MonthlyBusinessWorkDays(this.id,this.firstDayOfMonth,this.monthlyData,this.company,this.branch);
   }
}



}
