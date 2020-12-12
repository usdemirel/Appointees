package com.notsecure.Appointees.entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Client {
@Id
private Long id;
@NotNull
private String name;
private String email;
private String phone;
@Nullable
@OneToOne
private Address address;

 
 
}
