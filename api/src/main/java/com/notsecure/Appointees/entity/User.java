package com.notsecure.Appointees.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class User implements Serializable {
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
@OneToOne(cascade = CascadeType.PERSIST)
private Address address;

}