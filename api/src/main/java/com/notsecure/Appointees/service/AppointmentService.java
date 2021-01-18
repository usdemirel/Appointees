package com.notsecure.Appointees.service;

import com.notsecure.Appointees.entity.Appointment;
import javassist.NotFoundException;

import java.util.Optional;

public interface AppointmentService {

   Appointment save(Appointment appointment) throws Exception;
Iterable<Appointment> saveAll(Iterable<Appointment> iterable);
Optional<Appointment> findById(Long appointmentId) throws NotFoundException;
boolean existsById(Long appointmentId);
Iterable<Appointment> findAll();
Iterable<Appointment> findAllById(Iterable<Long> iterable);
long count();
void deleteById(Long aLong);
void delete(Appointment appointment);
void deleteAll(Iterable<Appointment> iterable);

}
