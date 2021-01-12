package com.notsecure.Appointees.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class Branch extends PublicInfo implements Serializable {
private String zoneId;
@JoinColumn(updatable = false)
@ManyToOne (fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
Company company;
private boolean separateBilling;
@OneToOne (fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
private AccountInfo accountInfo; // if null, then payments are handled by the company.

public Branch setActiveAccountFalse() {
   this.setActiveAccount(false);
   return this;
}


}
