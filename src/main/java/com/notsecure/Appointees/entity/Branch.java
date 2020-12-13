package com.notsecure.Appointees.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
public class Branch extends PublicInfo{
private String zoneId;
@JsonIgnore // Due to LAZY fetching caused issues, I've added the annotation SD - 12/12/2020
@ManyToOne (fetch = FetchType.LAZY)
Company company;
private boolean separateBilling;
@OneToOne (fetch = FetchType.LAZY)
private AccountInfo accountInfo; // if null, then payments are handled by the company.

public Branch setActiveAccountFalse() {
   this.setActiveAccount(false);
   return this;
}

}
