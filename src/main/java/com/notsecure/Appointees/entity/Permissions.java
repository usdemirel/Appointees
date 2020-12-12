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
public class Permissions {
   @Id
   private Long id;
//   .....
//   ....
   private boolean modifyUpcomingAppointments;
   private boolean addNewUser;
//   .....
//   ....
}
