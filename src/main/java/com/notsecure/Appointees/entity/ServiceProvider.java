package com.notsecure.Appointees.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ServiceProvider {
@Id
private Long id;
@OneToOne
private User user;
@OneToMany
private List<Service> services; //set?
@OneToOne
private Company company;
@OneToOne
private Branch branch;
private boolean serviceProviderAvailableOnBP; // is service provider is open to new appointments


}
