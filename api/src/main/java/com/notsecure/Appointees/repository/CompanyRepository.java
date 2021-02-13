package com.notsecure.Appointees.repository;

import com.notsecure.Appointees.entity.Company;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Long> {
   Optional<Company> findCompanyByBusinessName(String businessName);
   boolean existsByUniqueIdentifier(String uniqueIdentifier);
}
