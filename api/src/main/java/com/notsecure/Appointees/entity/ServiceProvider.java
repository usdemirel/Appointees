package com.notsecure.Appointees.entity;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class ServiceProvider implements Serializable {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Long id;
@OneToOne(cascade = CascadeType.MERGE)
//@Column(nullable = false, unique = true)
private User user;
@ManyToMany(cascade = CascadeType.DETACH) // an important update..
private List<Service> services; //set?
@OneToOne(cascade = CascadeType.DETACH)
private Company company;
@OneToOne(cascade = CascadeType.DETACH)
private Branch branch;
private boolean serviceProviderAvailableOnBP; // is service provider is open to new appointments


}
