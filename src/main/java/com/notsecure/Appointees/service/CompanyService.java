package com.notsecure.Appointees.service;

import com.notsecure.Appointees.entity.Branch;
import com.notsecure.Appointees.entity.Company;

import java.util.Optional;

public interface CompanyService {
Optional<Company> findCompanyById(Long companyId);
Optional<Company> findCompanyByName(String name);
Company save(Company company);

}
