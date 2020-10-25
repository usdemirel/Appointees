package com.notsecure.Appointees.repository;

import com.notsecure.Appointees.entity.Company;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CompanyRepository extends CrudRepository<Company, Long> {
 Optional<Company> findByName(String name);
}
