package com.notsecure.Appointees.repository;

import com.notsecure.Appointees.entity.UserRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends CrudRepository<UserRole, Long>, UserRoleRepositoryCustom {

List<UserRole> findUserRolesByUserId(Long userId);
List<UserRole> findUserRolesByUserIdAndActiveIsTrue(Long userId);
List<UserRole> findUserRolesByUserIdAndCompanyIdAndBranchIsNullAndActiveIsTrue(Long userId,Long companyId);
List<UserRole> findUserRolesByUserIdAndCompanyIsNullAndBranchIsNullAndActiveIsTrue(Long userId);
List<UserRole> findUserRolesByUserIdAndCompanyIdAndBranchIdAndActiveIsTrue(Long userId,Long companyId,Long branchId);

}
