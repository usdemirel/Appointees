package com.notsecure.Appointees.service;

import com.notsecure.Appointees.entity.Branch;
import com.notsecure.Appointees.entity.Company;
import com.notsecure.Appointees.model.ErrorMessages;
import com.notsecure.Appointees.repository.BranchRepository;
import com.notsecure.Appointees.repository.CompanyRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService{

@Autowired
CompanyRepository companyRepository;

@Autowired
BranchRepository branchRepository;


@Override
public Optional<Company> findCompanyById(Long companyId) throws NotFoundException {
 Optional<Company> company = companyRepository.findById(companyId);
 if (!company.isPresent()) throw new NotFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
 return company;
}

@Override
public Optional<Company> findCompanyByName(String name) {
 return companyRepository.findCompanyByBusinessName(name);
}

@Override
public Company save(Company company) throws Exception {
 Company saved = companyRepository.save(company);
 if(saved == null) throw new Exception(ErrorMessages.COULD_NOT_SAVE_RECORD.getErrorMessage());
 return saved;
}


}
