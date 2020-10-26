package com.notsecure.Appointees.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Service {
 @Id
 @GeneratedValue(strategy = GenerationType.AUTO)
 private Long id;
 private String name;
 private String description;
 private boolean isServiceAvailableOnBP;
 private double price;
 private boolean isPriceAvailableOnBP;
 private String directLinkToService;
 private int duration;
 private int bufferTime;
 private boolean isDurationVisibleOnBP;

public Service() {
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

public String getDescription() {
 return description;
}

public void setDescription(String description) {
 this.description = description;
}

public boolean isServiceAvailableOnBP() {
 return isServiceAvailableOnBP;
}

public void setServiceAvailableOnBP(boolean serviceAvailableOnBP) {
 isServiceAvailableOnBP = serviceAvailableOnBP;
}

public double getPrice() {
 return price;
}

public void setPrice(double price) {
 this.price = price;
}

public boolean isPriceAvailableOnBP() {
 return isPriceAvailableOnBP;
}

public void setPriceAvailableOnBP(boolean priceAvailableOnBP) {
 isPriceAvailableOnBP = priceAvailableOnBP;
}

public String getDirectLinkToService() {
 return directLinkToService;
}

public void setDirectLinkToService(String directLinkToService) {
 this.directLinkToService = directLinkToService;
}

public int getDuration() {
 return duration;
}

public void setDuration(int duration) {
 this.duration = duration;
}

public int getBufferTime() {
 return bufferTime;
}

public void setBufferTime(int bufferTime) {
 this.bufferTime = bufferTime;
}

public boolean isDurationVisibleOnBP() {
 return isDurationVisibleOnBP;
}

public void setDurationVisibleOnBP(boolean durationVisibleOnBP) {
 isDurationVisibleOnBP = durationVisibleOnBP;
}
}
