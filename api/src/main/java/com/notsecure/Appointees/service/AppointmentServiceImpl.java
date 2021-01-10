package com.notsecure.Appointees.service;

import com.notsecure.Appointees.entity.Appointment;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService{

@Override
public Iterable<Appointment> saveAll(Iterable<Appointment> iterable) {
   return null;
}

@Override
public Optional<Appointment> findById(Long appointmentId) {
   return Optional.empty();
}

@Override
public boolean existsById(Long appointmentId) {
   return false;
}

@Override
public Iterable<Appointment> findAll() {
   return null;
}

@Override
public Iterable<Appointment> findAllById(Iterable<Long> iterable) {
   return null;
}

@Override
public long count() {
   return 0;
}

@Override
public void deleteById(Long aLong) {

}

@Override
public void delete(Appointment appointment) {

}

@Override
public void deleteAll(Iterable<Appointment> iterable) {

}
}
