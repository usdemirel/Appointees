package com.notsecure.Appointees.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
//@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Company extends PublicInfo implements Serializable {

@OneToOne
private AccountInfo accountInfo;


}
