package com.notsecure.Appointees.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ServiceProvider {
@Id
private String email;
private String name;
private String description;
private String phone;
private String password;

public ServiceProvider() {
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
