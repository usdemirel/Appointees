package com.notsecure.Appointees.service;

import com.notsecure.Appointees.entity.Service;
import javassist.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface ServiceService {
   Optional<Service> findServiceById(Long id) throws NotFoundException;
   List<Service> findAllByBranchId(Long branchId) throws NotFoundException;
   Service saveService(Service service) throws Exception;
}
