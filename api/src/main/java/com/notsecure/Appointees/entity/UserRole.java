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
public class UserRole {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Long Id;
private LocalDateTime dateCreated;
private String personnelHighlights;
//private String role; //  MasterAdmin, BranchAdmin,BookingClerk,SeniorServiceProvider, JuniorServiceProvider, Observer
@OneToOne
private Permissions permissions;
private boolean roleValid;
private boolean defaultRole; // isdefaulRole: when logging in, the system will take the user to the page based on the default role
@OneToOne
private User roleAssignedBy;
@OneToOne(fetch = FetchType.LAZY)
private Company company;
@OneToOne(fetch = FetchType.LAZY)
private Branch branch;
@ManyToOne(fetch = FetchType.LAZY)
private User user;


}
