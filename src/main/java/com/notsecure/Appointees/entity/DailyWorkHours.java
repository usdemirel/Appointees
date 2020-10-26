package com.notsecure.Appointees.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class DailyWorkHours {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Long id;
@OneToMany
private List<WorkHours> workHours;

public DailyWorkHours() {
}

public Long getId() {
 return id;
}

public void setId(Long id) {
 this.id = id;
}

public List<WorkHours> getWorkHours() {
 return workHours;
}

public void setWorkHours(List<WorkHours> workHours) {
 this.workHours = workHours;
}

@Override
public String toString() {
 return "DailyWorkHours{" +
                 "id=" + id +
                 ", workHours=" + workHours +
                 '}';
}
}
