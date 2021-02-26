package com.notsecure.Appointees.service;

import com.notsecure.Appointees.entity.UserRole;
import com.notsecure.Appointees.model.ErrorMessages;
import com.notsecure.Appointees.repository.UserRepository;
import com.notsecure.Appointees.repository.UserRoleRepository;
import com.sun.xml.bind.v2.runtime.output.SAXOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserRoleServiceImpl implements UserRoleService {

@Autowired
UserRoleRepository userRoleRepository;

@Autowired
UserRepository userRepository;

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

@Override
public boolean authorizeUser(String expected) {
   final boolean[] result = new boolean[1];
   userRoleRepository.findUserRolesByUserIdAndActiveIsTrue(userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).get().getId())
                   .forEach(data -> result[0] = (result[0] || data.getRole().getPermissions().contains(expected)));
   return result[0];
}

@Override
public Map<String, List<Long>> retrievePermissions(String... permissions) {
   Long userId = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).get().getId();
   Map<String, List<Long>> result = new HashMap<>();
   result.put("userId", new ArrayList<>(Arrays.asList(userId)));
   List<UserRole> userRoles = userRoleRepository.findUserRolesByUserIdAndActiveIsTrue(userId);
   if (userRoles.isEmpty()) return result; // no roles is returned
   
   for (UserRole userRole : userRoles) {
      String currentPermissions = userRole.getRole().getPermissions();
      for (String permission : permissions) {
         if (currentPermissions.contains(permission)) {
            if (!result.containsKey(permission)) {
               if (userRole.getBranch().getId() == null)
                  result.put(permission, new ArrayList<>(Arrays.asList(userRole.getCompany().getId())));
               else result.put(permission, new ArrayList<>(Arrays.asList(userRole.getBranch().getId())));
            } else {
               if (userRole.getBranch().getId() == null)
                  result.get(permission).addAll(Arrays.asList(userRole.getCompany().getId()));
               else result.get(permission).addAll(Arrays.asList(userRole.getBranch().getId()));
            }
         }
      }
   }
   return result;
}

@Override
public Map<String, Set<Long>> retrieveBasePermissions(String... permissions) {
   Long userId = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).get().getId();
   Map<String, Set<Long>> result = new HashMap<>();
   result.put("userId", new HashSet<>(Arrays.asList(userId)));
   result.put("OWN", new HashSet<>());
   result.put("BR", new HashSet<>());
   result.put("CO", new HashSet<>());
   
   List<UserRole> userRoles = userRoleRepository.findUserRolesByUserIdAndActiveIsTrue(userId);
   if (userRoles.isEmpty()) return result; // no role is returned
   
   for (UserRole userRole : userRoles) {
      String currentPermissions = userRole.getRole().getPermissions();
      for (String permission : permissions) {
         if (currentPermissions.contains(permission)) {
            if (permission.contains("_OWN"))
               result.get("OWN").add(userRole.getBranch().getId() == null ? userRole.getCompany().getId() : userRole.getBranch().getId());
            else if (permission.contains("_BR"))
               result.get("BR").add(userRole.getBranch().getId() == null ? userRole.getCompany().getId() : userRole.getBranch().getId());
            else if (permission.contains("_CO"))
               result.get("CO").add(userRole.getBranch().getId() == null ? userRole.getCompany().getId() : userRole.getBranch().getId());
         }
      }
   }
   return result;
}

@Override
public Map<String, Set<Long>> retrieveFinalizedPermissions(Long id, String... permissions) {
   Long userId = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).get().getId();
   Map<String, Set<Long>> result = new HashMap<>();
   result.put("requesterUserId", new HashSet<>(Arrays.asList(userId)));
   result.put("permissionType", new HashSet<>());
   result.put("OWN", new HashSet<>());
   result.put("BR", new HashSet<>());
   result.put("CO", new HashSet<>());
   
   List<UserRole> userRoles = userRoleRepository.findUserRolesByUserIdAndActiveIsTrue(userId);
   if (userRoles.isEmpty()) {
      result.get("permissionType").add(0L);
      return result; // no role is returned
   }
   
   for (UserRole userRole : userRoles) {
      String currentPermissions = userRole.getRole().getPermissions();
      for (String permission : permissions) {
         if (currentPermissions.contains(permission)) {
            if (permission.contains("_OWN"))
               result.get("OWN").add(userRole.getBranch() == null ? userRole.getCompany().getId() : userRole.getBranch().getId());
            else if (permission.contains("_BR"))
               result.get("BR").add(userRole.getBranch() == null ? userRole.getCompany().getId() : userRole.getBranch().getId());
            else if (permission.contains("_CO"))
               result.get("CO").add(userRole.getBranch() == null ? userRole.getCompany().getId() : userRole.getBranch().getId());
         }
      }
   }
   
   System.out.println("id " + id);
   System.out.println("B " + findBranchIdsUserHasRoles(id));
   System.out.println("C "+ findCompanyIdsUserHasRoles(id));
   
   result.get("BR").retainAll(findBranchIdsUserHasRoles(id));
   result.get("CO").retainAll(findCompanyIdsUserHasRoles(id));
   
   if ((result.get("requesterUserId").stream().findFirst().get().longValue() == id && !result.get("OWN").isEmpty())){
      System.out.println("has the permission to view its own profile");
      result.get("permissionType").add(1L);
   }else if(!result.get("BR").isEmpty()){
      System.out.println("has the permission to view profile because the requester has the privilege to see the profiles of people working in the same branch");
      result.get("permissionType").add(2L);
   }else if(!result.get("CO").isEmpty()){
      System.out.println("has the permission to view profile because the requester has the privilege to see the profiles of people working in the same company");
      result.get("permissionType").add(3L);
   }else{
      System.out.println("No permission");
      result.get("permissionType").add(0L);
   }
   return result;
}

@Override
public Set<Long> findBranchIdsUserHasRoles(Long userId) {
   return userRoleRepository.findBranchIdsUserHasRoles(userId);
}

@Override
public Set<Long> findCompanyIdsUserHasRoles(Long userId) {
   return userRoleRepository.findCompanyIdsUserHasRoles(userId);
}


}
