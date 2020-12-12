package com.notsecure.Appointees.service;

import com.notsecure.Appointees.entity.Company;
import com.notsecure.Appointees.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements CompanyService{

@Autowired
CompanyRepository companyRepository;

@Override
public Company findById(Long id) {
 return (Company) companyRepository.findById(id).get();
}

@Override
public Company findByName(String name) {
// return companyRepository.findByName(name).get();
 return null;
}

}
