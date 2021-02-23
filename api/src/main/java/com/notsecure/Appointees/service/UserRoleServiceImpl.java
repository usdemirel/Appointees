package com.notsecure.Appointees.service;

import com.notsecure.Appointees.entity.UserRole;
import com.notsecure.Appointees.model.ErrorMessages;
import com.notsecure.Appointees.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {

@Autowired
UserRoleRepository userRoleRepository;

@Override
public List<UserRole> findAll() {
   List<UserRole> userRoles = new ArrayList<>();
   userRoleRepository.findAll().forEach(userRole -> userRoles.add(userRole));
   return userRoles;
}

@Override
public UserRole save(UserRole userRole) throws Exception {
   UserRole saved = userRoleRepository.save(userRole);
   if (saved == null) throw new Exception(ErrorMessages.COULD_NOT_SAVE_RECORD.getErrorMessage());
   return saved;
}

@Override
public List<UserRole> findUserRolesByUserId(Long userId) {
   return userRoleRepository.findUserRolesByUserId(userId);
}

@Override
public List<UserRole> findUserRolesByUserIdAndActiveIsTrue(Long userId) {
   return userRoleRepository.findUserRolesByUserIdAndActiveIsTrue(userId);
}

@Override
public List<UserRole> findUserRolesByUserIdAndCompanyIdAndBranchIsNullAndActiveIsTrue(Long userId, Long companyId) {
   return userRoleRepository.findUserRolesByUserIdAndCompanyIdAndBranchIsNullAndActiveIsTrue(userId, companyId);
}

@Override
public List<UserRole> findUserRolesByUserIdAndCompanyIsNullAndBranchIsNullAndActiveIsTrue(Long userId) {
   return userRoleRepository.findUserRolesByUserIdAndCompanyIsNullAndBranchIsNullAndActiveIsTrue(userId);
}

@Override
public List<UserRole> findUserRolesByUserIdAndCompanyIdAndBranchIdAndActiveIsTrue(Long userId, Long companyId, Long branchId) {
   return userRoleRepository.findUserRolesByUserIdAndCompanyIdAndBranchIdAndActiveIsTrue(userId, companyId, branchId);
}
}
