package com.notsecure.Appointees.service;

import com.notsecure.Appointees.entity.Appointment;
import com.notsecure.Appointees.entity.Client;
import com.notsecure.Appointees.entity.ServiceProvider;
import com.notsecure.Appointees.repository.AppointmentRepository;
import com.notsecure.Appointees.repository.ClientRepository;
import com.notsecure.Appointees.repository.ServiceProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ClientServiceImpl implements ClientService{

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private ServiceProviderRepository serviceProviderRepository;

   @Override
   public Set<Client> findAllClientsByCompanyId(Long companyId) {
       List<ServiceProvider> serviceProviderList = serviceProviderRepository.findAllByCompany_Id(companyId);
       Set<Client> clients= new HashSet<>();

                serviceProviderList
                                .stream()
                                    .parallel()
                                    .forEach(eachServiceProvider -> {
                                        List<Appointment> appointmentList = appointmentRepository.findAllByServiceProvider_Id(eachServiceProvider.getId());
                                                appointmentList
                                                            .stream()
                                                            .forEach(eachAppointment -> {
                                                                clients.add(eachAppointment.getClient());
                                                            });
                                    });

       return clients;
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

    @Override
    public void deactivateById(Long clientId) {

    }
}
