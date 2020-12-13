package com.notsecure.Appointees.service;

import com.notsecure.Appointees.entity.Branch;
import com.notsecure.Appointees.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BranchServiceImpl implements BranchService {
@Autowired
BranchRepository branchRepository;

@Override
public Optional<Branch> findBranchById(Long branchId) {
   return branchRepository.findById(branchId);
}

@Override
public List<Branch> findBranchesByCompanyId(Long companyId) {
   return branchRepository.findBranchesByCompanyId(companyId);
}

@Override
public Branch save(Branch branch) {
   return branchRepository.save(branch);
}
}
