package com.notsecure.Appointees.repository;

import com.notsecure.Appointees.entity.WeeklyAppointmentsPerServiceProvider;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeeklyAppointmentsPerServiceProviderRepository extends CrudRepository<WeeklyAppointmentsPerServiceProvider,Long> {
}
