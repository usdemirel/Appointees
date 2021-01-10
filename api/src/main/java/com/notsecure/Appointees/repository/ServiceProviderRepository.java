package com.notsecure.Appointees.repository;

import com.notsecure.Appointees.entity.ServiceProvider;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceProviderRepository extends CrudRepository<ServiceProvider, Long> {

    List<ServiceProvider> findAllByCompany_Id(Long companyId);
}
