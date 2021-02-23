package com.notsecure.Appointees.service;

import com.notsecure.Appointees.entity.UserRole;

import java.util.List;

public interface UserRoleService {
   
   List<UserRole> findAll();
   UserRole save(UserRole userRole) throws Exception;
List<UserRole> findUserRolesByUserId(Long userId);
List<UserRole> findUserRolesByUserIdAndActiveIsTrue(Long userId);
List<UserRole> findUserRolesByUserIdAndCompanyIdAndBranchIsNullAndActiveIsTrue(Long userId,Long companyId);
List<UserRole> findUserRolesByUserIdAndCompanyIsNullAndBranchIsNullAndActiveIsTrue(Long userId);
List<UserRole> findUserRolesByUserIdAndCompanyIdAndBranchIdAndActiveIsTrue(Long userId,Long companyId,Long branchId);

}
