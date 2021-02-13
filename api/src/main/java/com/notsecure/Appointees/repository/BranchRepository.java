package com.notsecure.Appointees.repository;

import com.notsecure.Appointees.entity.Branch;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BranchRepository extends CrudRepository<Branch, Long> {
//   Optional<Branch> findBranchById(Long branchId);
//Optional<Branch>
List<Branch> findBranchesByCompanyId(Long companyId);
boolean existsByCompanyIdAndUniqueIdentifier(Long companyId, String uniqueIdentifier);

}
