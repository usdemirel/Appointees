package com.notsecure.Appointees.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class ServiceProvider implements Serializable {
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
