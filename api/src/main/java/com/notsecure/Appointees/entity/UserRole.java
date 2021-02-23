package com.notsecure.Appointees.entity;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class UserRole {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Long id;
private LocalDateTime timestamp;

@JoinColumn(updatable = false)
@ManyToOne(cascade = CascadeType.DETACH)
private User user;

@JoinColumn(updatable = false)
@OneToOne(cascade = CascadeType.DETACH)
private Role role;

private boolean active;
@JoinColumn(updatable = false)
@OneToOne(cascade = CascadeType.DETACH)
private Company company;

@JoinColumn(updatable = false)
@OneToOne(cascade = CascadeType.DETACH)
private Branch branch;

}