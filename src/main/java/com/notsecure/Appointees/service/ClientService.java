package com.notsecure.Appointees.service;

import com.notsecure.Appointees.entity.Branch;
import com.notsecure.Appointees.entity.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {

    List<Client> findClientsByCompany(Long companyId);
Optional<Client> findById(Long clientId);
Client save(Client client);
boolean existsById(Long clientId);
void delete(Client client);
void deleteById(Long clientId);

}