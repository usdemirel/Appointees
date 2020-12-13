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

@OneToOne
private AccountInfo accountInfo;


}
