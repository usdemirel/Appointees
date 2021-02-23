package com.notsecure.Appointees.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class Role implements Serializable {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Long id;
private LocalDateTime timeStamp;
private String title;
private String description;
private boolean active;
private String permissions;

@JoinColumn(updatable = false)
@OneToOne
private User createdBy;

@JoinColumn(updatable = false)
@OneToOne
private User modifiedBy;

@JoinColumn(updatable = false)
@OneToOne
private Company companyCreatedFor;

@JoinColumn(updatable = false)
@OneToOne
private Branch branchCreatedFor;
   
}