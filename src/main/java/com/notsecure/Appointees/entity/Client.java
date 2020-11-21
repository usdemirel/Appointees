package com.notsecure.Appointees.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Client {
 @Id
 private Long id;
 private String name;
 private String email;
 private String phone;
 private String address;
 //abc

 
 
}
