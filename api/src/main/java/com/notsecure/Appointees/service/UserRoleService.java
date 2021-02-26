package com.notsecure.Appointees.service;

import com.notsecure.Appointees.entity.UserRole;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface UserRoleService {
   
   List<UserRole> findAll();
   UserRole save(UserRole userRole) throws Exception;
List<UserRole> findUserRolesByUserId(Long userId);
List<UserRole> findUserRolesByUserIdAndActiveIsTrue(Long userId);
List<UserRole> findUserRolesByUserIdAndCompanyIdAndBranchIsNullAndActiveIsTrue(Long userId,Long companyId);
List<UserRole> findUserRolesByUserIdAndCompanyIsNullAndBranchIsNullAndActiveIsTrue(Long userId);
List<UserRole> findUserRolesByUserIdAndCompanyIdAndBranchIdAndActiveIsTrue(Long userId,Long companyId,Long branchId);
boolean authorizeUser(String expected);
Set<Long> findBranchIdsUserHasRoles(Long userId);
Set<Long> findCompanyIdsUserHasRoles(Long userId);
Map<String,List<Long>> retrievePermissions(String... permissions);
Map<String,Set<Long>> retrieveBasePermissions(String... permissions);
Map<String,Set<Long>> retrieveFinalizedPermissions(Long id, String... permissions);




}
