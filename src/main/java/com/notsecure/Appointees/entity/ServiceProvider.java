package com.notsecure.Appointees.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class ServiceProvider {
@Id
private String email;
private String name;
private String displayName;
private String description;
private String phone;
private String password;
@OneToMany
private List<Service> service;
@OneToOne
Company company;

public ServiceProvider() {
}

public List<Service> getService() {
 return service;
}

public void setService(List<Service> service) {
 this.service = service;
}

public Company getCompany() {
 return company;
}

public void setCompany(Company company) {
 this.company = company;
}

public String getEmail() {
 return email;
}

public void setEmail(String email) {
 this.email = email;
}

public String getName() {
 return name;
}

public void setName(String name) {
 this.name = name;
}

public String getDisplayName() {
 return displayName;
}

public void setDisplayName(String displayName) {
 this.displayName = displayName;
}

public String getDescription() {
 return description;
}

public void setDescription(String description) {
 this.description = description;
}

public String getPhone() {
 return phone;
}

public void setPhone(String phone) {
 this.phone = phone;
}

public String getPassword() {
 return password;
}

public void setPassword(String password) {
 this.password = password;
}
}
