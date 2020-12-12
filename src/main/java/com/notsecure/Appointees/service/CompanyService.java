package com.notsecure.Appointees.service;

import com.notsecure.Appointees.entity.Company;

import java.util.Optional;

public interface CompanyService {
Company findById(Long id);
Company findByName(String name);
}
