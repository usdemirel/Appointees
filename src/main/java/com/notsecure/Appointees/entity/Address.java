package com.notsecure.Appointees.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Address {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Long id;
private String addressLine1;
private String addressLine2;
private String city;
private String state;
private String zipCode;
private String country;
}
