package com.notsecure.Appointees.service;

import com.notsecure.Appointees.entity.Service;
import javassist.NotFoundException;

import java.util.Optional;

public interface ServiceService {
   Optional<Service> findServiceById(Long id) throws NotFoundException;
   Service saveService(Service service) throws Exception;
}
