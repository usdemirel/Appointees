package com.notsecure.Appointees.service;

import com.notsecure.Appointees.entity.Client;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService{


   @Override
   public List<Client> findClientsByCompany(Long companyId) {
      return null;
   }

@Override
public Optional<Client> findById(Long clientId) {
   return Optional.empty();
}

@Override
public Client save(Client client) {
   return null;
}

@Override
public boolean existsById(Long clientId) {
   return false;
}

@Override
public void delete(Client client) {

}

@Override
public void deleteById(Long clientId) {

}
}
