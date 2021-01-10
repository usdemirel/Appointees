package com.notsecure.Appointees.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class UserRoleModificationHistory extends UserRole{
private LocalDateTime dateModified;
private String reasonToUpdate;
@ManyToOne(fetch = FetchType.LAZY)
private User roleModifiedBy;



}
