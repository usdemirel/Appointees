package com.notsecure.Appointees.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

// No history table is needed, all messages and their modifications will be logged!
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Note {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Long id;
private LocalDateTime dateCreated;
private String note;
@OneToOne
User user;
@ManyToOne
private Appointment appointment;



}
