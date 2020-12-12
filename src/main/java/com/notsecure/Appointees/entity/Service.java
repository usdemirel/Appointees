package com.notsecure.Appointees.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Service {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Long id;
private String name;
@Column(length = 1000)
private String description;
private boolean isServiceAvailableOnBP;
private Double price;
private boolean isPriceAvailableOnBP;
private String directLinkToService; //?
private int duration;
private int bufferTime;
private boolean isDurationVisibleOnBP;
// This is to say if any branch can copy the service from the company or a branch.
private boolean serviceInfoPublicToAllBranches;
private int allowedCancellationTimeWindowPriorToAppointment; // should be entered in minutes;
/*For example, this value can be set to 90 at max to limit clients setting the appointment for 90 days later at most*/
private int allowedDaysInAdvanceAppointmentBookings;
@OneToOne
Company company;
@OneToOne @Nullable
Branch branch; // Company can create a service for branches to copy. At that time branch will be null.



}
