package com.notsecure.Appointees.service;

import com.notsecure.Appointees.entity.Client;

import java.util.Optional;

public interface ClientService {

Optional<Client> findById(Long clientId);
Client save(Client client);
boolean existsById(Long clientId);
void delete(Client client);
void deleteById(Long clientId);

}
