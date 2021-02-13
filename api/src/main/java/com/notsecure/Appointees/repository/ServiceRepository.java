package com.notsecure.Appointees.repository;

import com.notsecure.Appointees.entity.Service;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ServiceRepository extends CrudRepository<Service, Long> {
   List<Service> findAllByBranchId(Long branchId);
}
