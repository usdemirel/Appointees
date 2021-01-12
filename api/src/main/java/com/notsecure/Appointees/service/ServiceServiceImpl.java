package com.notsecure.Appointees.service;

import com.notsecure.Appointees.entity.Company;
import com.notsecure.Appointees.model.ErrorMessages;
import com.notsecure.Appointees.repository.ServiceRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceServiceImpl implements ServiceService{
   @Autowired
   ServiceRepository serviceRepository;
   
@Override
public Optional<com.notsecure.Appointees.entity.Service> findServiceById(Long id) throws NotFoundException {
   Optional<com.notsecure.Appointees.entity.Service> service = serviceRepository.findById(id);
   if(service.isEmpty()) throw new NotFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage() + "with id of" + id);
   return service;
}

@Override
public com.notsecure.Appointees.entity.Service saveService(com.notsecure.Appointees.entity.Service service) throws Exception {
   com.notsecure.Appointees.entity.Service saved = serviceRepository.save(service);
   if(saved == null) throw new Exception(ErrorMessages.COULD_NOT_SAVE_RECORD.getErrorMessage());
   return saved;
}

}
