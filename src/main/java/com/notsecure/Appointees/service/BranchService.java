package com.notsecure.Appointees.service;

import com.notsecure.Appointees.entity.Branch;

import java.util.List;
import java.util.Optional;

public interface BranchService {
   Optional<Branch> findBranchById(Long branchId);
   List<Branch> findBranchesByCompanyId(Long companyId);
   Branch save(Branch branch);
}
