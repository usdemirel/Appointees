package com.notsecure.Appointees.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class AccountInfo {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Long id;
private String planType;
private LocalDate validUntil;
private Integer numberOfBookingsLeft;
}
