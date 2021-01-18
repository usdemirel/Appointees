package com.notsecure.Appointees.entity;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Appointment {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Long id;
private LocalDateTime dateCreated;
private LocalDateTime appointmentDateTime;
private String bookingConfirmationNumber;
private boolean cancelled;

@OneToOne(cascade = CascadeType.PERSIST)
private Client client;
@JoinColumn(updatable = false)
@OneToOne(cascade = CascadeType.DETACH)
private Company company;
@JoinColumn(updatable = false)
@OneToOne(cascade = CascadeType.DETACH)
private Branch branch;
@JoinColumn(updatable = false)
@OneToOne(cascade = CascadeType.DETACH)
private ServiceProvider serviceProvider;
@JoinColumn(updatable = false)
@OneToOne(cascade = CascadeType.DETACH)
private Service service;

/*
private boolean isRecurring;
private int numOfRecurringTimes;

instead of putting it here, create a method to copy the appointment for desired dates and times
This can be done by seniorServiceProvider and all the way up
*/

}
