package com.notsecure.Appointees.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Entity
public class Appointment {
 @Id
private Long id;
 private LocalDateTime recordDateTime;
private boolean isCancelled;
private boolean isRecurring;
 private int numOfRecurringTimes;
 private LocalDateTime inDateTime;
 private LocalDateTime outDateTime;
 @OneToOne
 private Service service;
 @OneToOne
 private ServiceProvider serviceProvider;
 @OneToOne
 private Client client;

public Appointment() {
}

public Long getId() {
 return id;
}

public void setId(Long id) {
 this.id = id;
}

public LocalDateTime getRecordDateTime() {
 return recordDateTime;
}

public void setRecordDateTime(LocalDateTime recordDateTime) {
 this.recordDateTime = recordDateTime;
}

public boolean isCancelled() {
 return isCancelled;
}

public void setCancelled(boolean cancelled) {
 isCancelled = cancelled;
}

public boolean isRecurring() {
 return isRecurring;
}

public void setRecurring(boolean recurring) {
 isRecurring = recurring;
}

public int getNumOfRecurringTimes() {
 return numOfRecurringTimes;
}

public void setNumOfRecurringTimes(int numOfRecurringTimes) {
 this.numOfRecurringTimes = numOfRecurringTimes;
}

public LocalDateTime getInDateTime() {
 return inDateTime;
}

public void setInDateTime(LocalDateTime inDateTime) {
 this.inDateTime = inDateTime;
}

public LocalDateTime getOutDateTime() {
 return outDateTime;
}

public void setOutDateTime(LocalDateTime outDateTime) {
 this.outDateTime = outDateTime;
}

public Service getService() {
 return service;
}

public void setService(Service service) {
 this.service = service;
}

public ServiceProvider getServiceProvider() {
 return serviceProvider;
}

public void setServiceProvider(ServiceProvider serviceProvider) {
 this.serviceProvider = serviceProvider;
}

public Client getClient() {
 return client;
}

public void setClient(Client client) {
 this.client = client;
}
}
