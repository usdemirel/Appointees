package com.notsecure.Appointees.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.LazyGroup;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Entity
@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class Branch extends PublicInfo implements Serializable {
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
