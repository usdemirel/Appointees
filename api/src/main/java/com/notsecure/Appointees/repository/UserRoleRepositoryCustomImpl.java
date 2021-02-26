package com.notsecure.Appointees.repository;

import com.notsecure.Appointees.entity.Branch;
import com.notsecure.Appointees.entity.Company;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UserRoleRepositoryCustomImpl implements UserRoleRepositoryCustom {

@PersistenceContext
EntityManager entityManager;

@Override
public Set<Long> findBranchIdsUserHasRoles(Long userId) {
   String qlQuery = "SELECT r.branch FROM UserRole r WHERE r.user.id = " + userId;
   Query query = entityManager.createQuery(qlQuery);
   return ((List<Branch>) query.getResultList()).stream().map(data -> data.getId()).collect(Collectors.toSet());
}

@Override
public Set<Long> findCompanyIdsUserHasRoles(Long userId) {
   String qlQuery = "SELECT r.company FROM UserRole r WHERE r.user.id = " + userId;
   Query query = entityManager.createQuery(qlQuery);
   return ((List<Company>) query.getResultList()).stream().map(data -> data.getId()).collect(Collectors.toSet());
}
}
