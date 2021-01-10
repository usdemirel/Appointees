package com.notsecure.Appointees.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity

@Getter
@Setter
@NoArgsConstructor
public class User {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Long id;
private LocalDateTime dateCreated;
private String email;
private String password;
private String firstName;
private String lastName;
private String displayName; // for public view
private String phoneNumber;
@OneToOne
private Address address;

public User(Long id, String firstName) {
 this.id = id;
 this.firstName = firstName;
}

}
