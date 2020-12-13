package com.notsecure.Appointees.service;

import com.notsecure.Appointees.entity.Appointment;

import java.util.Optional;

public interface AppointmentService {

Iterable<Appointment> saveAll(Iterable<Appointment> iterable);
Optional<Appointment> findById(Long appointmentId);
boolean existsById(Long appointmentId);
Iterable<Appointment> findAll();
Iterable<Appointment> findAllById(Iterable<Long> iterable);
long count();
void deleteById(Long aLong);
void delete(Appointment appointment);
void deleteAll(Iterable<Appointment> iterable);

}
