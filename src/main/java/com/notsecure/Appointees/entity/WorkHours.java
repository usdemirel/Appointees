package com.notsecure.Appointees.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalTime;

@Entity
public class WorkHours {
 @Id
 @GeneratedValue(strategy = GenerationType.AUTO)
 private Long id;
 private LocalTime init;
 private LocalTime end;

public WorkHours() {
}

public Long getId() {
 return id;
}

public void setId(Long id) {
 this.id = id;
}

public LocalTime getInit() {
 return init;
}

public void setInit(LocalTime init) {
 this.init = init;
}

public LocalTime getEnd() {
 return end;
}

public void setEnd(LocalTime end) {
 this.end = end;
}

@Override
public String toString() {
 return "WorkHours{" +
                 "id=" + id +
                 ", init=" + init +
                 ", end=" + end +
                 '}';
}
}
