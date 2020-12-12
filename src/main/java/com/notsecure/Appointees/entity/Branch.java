package com.notsecure.Appointees.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Branch extends PublicInfo{
//@Id
//@GeneratedValue(strategy = GenerationType.AUTO)
//private Long id;
//@OneToOne
//private PublicInfo publicInfo;
private String zoneId;
@ManyToOne
Company company;
private boolean separateBilling;
@OneToOne
private AccountInfo accountInfo; // if null, then payments are handled by the company.

}
