package com.notsecure.Appointees.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Service implements Serializable {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Long id;
private String name; // private name for the company
private String servicePageTitle; // public for clients
@Column(length = 1000)
private String description;
private boolean serviceAvailableOnBP;
private Double price;
private boolean priceAvailableOnBP;
private String imageLink; //?
private int duration;
private int bufferTime;
private boolean durationVisibleOnBP;
// This is to say if any branch can copy the service from the company or a branch.
private boolean serviceInfoPublicToAllBranches;
private int allowedCancellationTimeWindowPriorToAppointment; // should be entered in minutes;
/*For example, this value can be set to 90 at max to limit clients setting the appointment for 90 days later at most*/
private int allowedDaysInAdvanceAppointmentBookings;
@OneToOne(cascade = CascadeType.DETACH)
Company company;
@OneToOne(cascade = CascadeType.DETACH)
@Nullable
Branch branch; // Company can create a service for branches to copy. At that time branch will be null.



}
