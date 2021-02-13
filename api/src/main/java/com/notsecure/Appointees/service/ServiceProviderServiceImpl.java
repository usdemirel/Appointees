package com.notsecure.Appointees.service;

import com.notsecure.Appointees.entity.ServiceProvider;
import com.notsecure.Appointees.model.ErrorMessages;
import com.notsecure.Appointees.repository.ServiceProviderRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
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
   if(serviceProvider.getId() == null && serviceProviderRepository.existsByUserId(serviceProvider.getUser().getId()))
      throw new DuplicateKeyException(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());
   ServiceProvider saved = serviceProviderRepository.save(serviceProvider);
   if(saved == null) throw new Exception(ErrorMessages.COULD_NOT_SAVE_RECORD.getErrorMessage());
   return saved;
}

@Override
public boolean existsByUserId(Long userId) {
   return serviceProviderRepository.existsByUserId(userId);
}

@Override
public List<ServiceProvider> findAllByBranchId(Long branchId) throws NotFoundException {
   List<ServiceProvider> serviceProviders = serviceProviderRepository.findAllByBranchId(branchId);
   if(serviceProviders.isEmpty()) throw new NotFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage() + " id: " + branchId);
   return serviceProviders;
}

@Override
public List<ServiceProvider> findAllByServicesId(Long serviceId) throws NotFoundException {
   List<ServiceProvider> serviceProviders = serviceProviderRepository.findAllByServicesId(serviceId);
   if(serviceProviders.isEmpty()) throw new NotFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage() + " id: " + serviceId);
   return serviceProviders;
}

@Override
public List<ServiceProvider> findAllByBranchIdAndServicesId(Long branchId, Long serviceId) throws NotFoundException {
   List<ServiceProvider> serviceProviders = serviceProviderRepository.findAllByBranchIdAndServicesId(branchId,serviceId);
   if(serviceProviders.isEmpty()) throw new NotFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
   return serviceProviders;
}

}
