package com.notsecure.Appointees.repository;

import com.notsecure.Appointees.entity.Permission;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends CrudRepository<Permission,String> {

}
