package com.notsecure.Appointees.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
 @Id
 private String username;
 private String password;
 private String firstName;
 private String lastName;
 private String nickName;
 private String role;
 private String phoneNumber;

public User() {
}

public String getUsername() {
 return username;
}

public void setUsername(String username) {
 this.username = username;
}

public String getPassword() {
 return password;
}

public void setPassword(String password) {
 this.password = password;
}

public String getFirstName() {
 return firstName;
}

public void setFirstName(String firstName) {
 this.firstName = firstName;
}

public String getLastName() {
 return lastName;
}

public void setLastName(String lastName) {
 this.lastName = lastName;
}

public String getNickName() {
 return nickName;
}

public void setNickName(String nickName) {
 this.nickName = nickName;
}

public String getRole() {
 return role;
}

public void setRole(String role) {
 this.role = role;
}

public String getPhoneNumber() {
 return phoneNumber;
}

public void setPhoneNumber(String phoneNumber) {
 this.phoneNumber = phoneNumber;
}
}
