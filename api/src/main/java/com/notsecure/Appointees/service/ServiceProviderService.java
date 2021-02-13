package com.notsecure.Appointees.service;

import com.notsecure.Appointees.entity.ServiceProvider;
import javassist.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface ServiceProviderService {
   Optional<ServiceProvider> findServiceProviderById(Long serviceProviderId) throws NotFoundException;
   List<ServiceProvider> findAllByCompanyId(Long companyId) throws NotFoundException;
   ServiceProvider saveServiceProvider(ServiceProvider serviceProvider) throws Exception;
   boolean existsByUserId(Long userId);
   List<ServiceProvider> findAllByBranchId(Long branchId) throws NotFoundException;
   List<ServiceProvider> findAllByServicesId(Long serviceId) throws NotFoundException;
   List<ServiceProvider> findAllByBranchIdAndServicesId(Long branchId, Long serviceId) throws NotFoundException;
   
   
}
