package com.notsecure.Appointees.service;

import com.notsecure.Appointees.entity.ServiceProvider;
import com.notsecure.Appointees.model.ErrorMessages;
import com.notsecure.Appointees.repository.ServiceProviderRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceProviderServiceImpl implements ServiceProviderService{
   @Autowired
   ServiceProviderRepository serviceProviderRepository;

@Override
public Optional<ServiceProvider> findServiceProviderById(Long serviceProviderId) throws NotFoundException {
   Optional<ServiceProvider> serviceProvider = serviceProviderRepository.findById(serviceProviderId);
   if(serviceProvider.isEmpty()) throw new NotFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage() + " id: " + serviceProviderId);
   return serviceProvider;
}

@Override
public List<ServiceProvider> findAllByCompanyId(Long companyId) throws NotFoundException{
   List<ServiceProvider> serviceProviderList = serviceProviderRepository.findAllByCompanyId(companyId);
   if(serviceProviderList.size()==0) throw new NotFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage() + "with the given company Id of " + companyId);
   return serviceProviderList;
}

@Override
public ServiceProvider saveServiceProvider(ServiceProvider serviceProvider) throws Exception{
   ServiceProvider saved = serviceProviderRepository.save(serviceProvider);
   if(saved == null) throw new Exception(ErrorMessages.COULD_NOT_SAVE_RECORD.getErrorMessage());
   return saved;
}

}
