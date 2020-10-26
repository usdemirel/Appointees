package com.notsecure.Appointees.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class Note {
@Id
private Long id;
private String explanation;
private LocalDateTime inDateTime;
private LocalDateTime endDateTime;
private boolean isBlock;
private String availableTo; // who can see the notes
@ManyToOne
private Appointment appointment;

public Note() {
}

public Long getId() {
 return id;
}

public void setId(Long id) {
 this.id = id;
}

public String getExplanation() {
 return explanation;
}

public void setExplanation(String explanation) {
 this.explanation = explanation;
}

public LocalDateTime getInDateTime() {
 return inDateTime;
}

public void setInDateTime(LocalDateTime inDateTime) {
 this.inDateTime = inDateTime;
}

public LocalDateTime getEndDateTime() {
 return endDateTime;
}

public void setEndDateTime(LocalDateTime endDateTime) {
 this.endDateTime = endDateTime;
}

public boolean isBlock() {
 return isBlock;
}

public void setBlock(boolean block) {
 isBlock = block;
}

public String getAvailableTo() {
 return availableTo;
}

public void setAvailableTo(String availableTo) {
 this.availableTo = availableTo;
}
}
