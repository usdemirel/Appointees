package com.notsecure.Appointees.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Company extends PublicInfo{
//@Id
//@GeneratedValue(strategy = GenerationType.AUTO)
//private Long id;
//@OneToOne
//private PublicInfo publicInfo;

@OneToOne
private AccountInfo accountInfo;


}
