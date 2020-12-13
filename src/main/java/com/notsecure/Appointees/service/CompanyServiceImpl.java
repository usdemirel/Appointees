package com.notsecure.Appointees.service;

import com.notsecure.Appointees.entity.Branch;
import com.notsecure.Appointees.entity.Company;
import com.notsecure.Appointees.repository.BranchRepository;
import com.notsecure.Appointees.repository.CompanyRepository;
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
public Optional<Company> findCompanyById(Long companyId) {
 return companyRepository.findById(companyId);
}

@Override
public Optional<Company> findCompanyByName(String name) {
// return companyRepository.findByName(name).get();
 return null;
}



}
