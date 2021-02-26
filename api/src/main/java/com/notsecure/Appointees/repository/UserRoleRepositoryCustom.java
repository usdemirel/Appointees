package com.notsecure.Appointees.repository;

import java.util.Set;

public interface UserRoleRepositoryCustom {
Set<Long> findBranchIdsUserHasRoles(Long userId);
Set<Long> findCompanyIdsUserHasRoles(Long userId);
}
