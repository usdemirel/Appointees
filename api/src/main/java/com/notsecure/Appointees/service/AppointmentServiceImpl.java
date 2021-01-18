package com.notsecure.Appointees.service;

import com.notsecure.Appointees.entity.Appointment;
import com.notsecure.Appointees.model.ErrorMessages;
import com.notsecure.Appointees.repository.AppointmentRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {

@Autowired
AppointmentRepository appointmentRepository;

@Autowired
WeeklyAppointmentsPerServiceProviderService weeklyAppointmentsPerServiceProviderService;

@Override
public Appointment save(Appointment appointment) throws Exception {
   Appointment saved = appointmentRepository.save(appointment);
   if(saved==null) throw new Exception(ErrorMessages.COULD_NOT_SAVE_RECORD.getErrorMessage());
   return saved;
}

@Override
public Iterable<Appointment> saveAll(Iterable<Appointment> iterable) {
   return appointmentRepository.saveAll(iterable);
}

@Override
public Optional<Appointment> findById(Long appointmentId) throws NotFoundException {
   Optional<Appointment> appointment = appointmentRepository.findById(appointmentId);
   if (appointment.isEmpty()) throw new NotFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
   return appointment;
}

@Override
public boolean existsById(Long appointmentId) {
   return appointmentRepository.existsById(appointmentId);
}

@Override
public Iterable<Appointment> findAll() {
   return appointmentRepository.findAll();
}

@Override
public Iterable<Appointment> findAllById(Iterable<Long> iterable) {
   return appointmentRepository.findAllById(iterable);
}

@Override
public long count() {
   return appointmentRepository.count();
}

@Override
public void deleteById(Long aLong) {
   appointmentRepository.deleteById(aLong);
}

@Override
public void delete(Appointment appointment) {
   appointmentRepository.delete(appointment);
}

@Override
public void deleteAll(Iterable<Appointment> iterable) {
   appointmentRepository.deleteAll(iterable);
}
}