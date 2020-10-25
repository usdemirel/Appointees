package com.notsecure.Appointees.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Client {
 @Id
 private Long id;
 private String name;
 private String email;
 private String phone;
 private String address;

public Client() {
}

public Long getId() {
 return id;
}

public void setId(Long id) {
 this.id = id;
}

public String getName() {
 return name;
}

public void setName(String name) {
 this.name = name;
}

public String getEmail() {
 return email;
}

public void setEmail(String email) {
 this.email = email;
}

public String getPhone() {
 return phone;
}

public void setPhone(String phone) {
 this.phone = phone;
}

public String getAddress() {
 return address;
}

public void setAddress(String address) {
 this.address = address;
}
}
