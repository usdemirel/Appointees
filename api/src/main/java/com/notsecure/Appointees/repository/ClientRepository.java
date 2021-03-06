package com.notsecure.Appointees.repository;

import com.notsecure.Appointees.entity.Client;
import com.notsecure.Appointees.entity.WeeklyDefaultWorkHours;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {

    List<Client> findAllById(Long clientId);
}
