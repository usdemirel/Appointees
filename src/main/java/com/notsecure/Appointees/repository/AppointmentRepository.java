package com.notsecure.Appointees.repository;

import com.notsecure.Appointees.entity.Appointment;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AppointmentRepository extends CrudRepository<Appointment,Long> {

@Override
Appointment save(Appointment appointment);

}
