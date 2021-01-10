package com.notsecure.Appointees.service;

import com.notsecure.Appointees.entity.Branch;
import com.notsecure.Appointees.entity.Client;
import javassist.NotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ClientService {

Set<Client> findAllClientsByCompanyId(Long companyId) throws NotFoundException;
Optional<Client> findById(Long clientId) throws NotFoundException;
Client save(Client client);
boolean existsById(Long clientId);
void delete(Client client);
void deleteById(Long clientId);
void deactivateById(Long clientId);

}
