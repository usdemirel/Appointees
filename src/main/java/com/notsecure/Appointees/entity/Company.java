package com.notsecure.Appointees.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Company {
 @Id
 @GeneratedValue(strategy = GenerationType.AUTO)
 private Long id;
 @Column(unique = true)
 private String name;
 private String email;
 private String phone;
 private String businessCategory;
 private String workAddress;
 private String pageTitle;
 private String companyDescription;
 private String planType;
 private LocalDate validUntil;
 private Integer numberOfBookingsLeft;
 private String wehsiteLink;
 
public Company() {
}

public String getWehsiteLink() {
 return wehsiteLink;
}

public void setWehsiteLink(String wehsiteLink) {
 this.wehsiteLink = wehsiteLink;
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

public String getBusinessCategory() {
 return businessCategory;
}

public void setBusinessCategory(String businessCategory) {
 this.businessCategory = businessCategory;
}

public String getWorkAddress() {
 return workAddress;
}

public void setWorkAddress(String workAddress) {
 this.workAddress = workAddress;
}

public String getPageTitle() {
 return pageTitle;
}

public void setPageTitle(String pageTitle) {
 this.pageTitle = pageTitle;
}

public String getCompanyDescription() {
 return companyDescription;
}

public void setCompanyDescription(String companyDescription) {
 this.companyDescription = companyDescription;
}

public String getPlanType() {
 return planType;
}

public void setPlanType(String planType) {
 this.planType = planType;
}

public LocalDate getValidUntil() {
 return validUntil;
}

public void setValidUntil(LocalDate validUntil) {
 this.validUntil = validUntil;
}

public Integer getNumberOfBookingsLeft() {
 return numberOfBookingsLeft;
}

public void setNumberOfBookingsLeft(Integer numberOfBookingsLeft) {
 this.numberOfBookingsLeft = numberOfBookingsLeft;
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
}
