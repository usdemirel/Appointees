package com.notsecure.Appointees.repository;

import com.notsecure.Appointees.entity.Service;
import org.springframework.data.repository.CrudRepository;

public interface ServiceRepository extends CrudRepository<Service, Long> {
}
