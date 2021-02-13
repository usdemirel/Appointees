package com.notsecure.Appointees.service;

import com.notsecure.Appointees.entity.Branch;
import javassist.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface BranchService {
   Optional<Branch> findBranchById(Long branchId) throws NotFoundException;
   List<Branch> findBranchesByCompanyId(Long companyId) throws NotFoundException;
   Branch save(Branch branch) throws Exception;
   
}