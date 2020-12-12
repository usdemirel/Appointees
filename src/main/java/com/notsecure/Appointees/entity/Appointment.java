package com.notsecure.Appointees.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
@NoArgsConstructor
public class Appointment {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Long id;
private LocalDateTime dateCreated;
private LocalDateTime appointmentDateTime;
private String bookingConfirmationNumber;
private boolean cancelled;
@OneToOne
private Client client;
@OneToOne
private Service service;
@OneToOne
private ServiceProvider serviceProvider;

/*
private boolean isRecurring;
private int numOfRecurringTimes;

instead of putting it here, create a method to copy the appointment for desired dates and times
This can be done by seniorServiceProvider and all the way up
*/

}
