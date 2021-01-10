package com.notsecure.Appointees.repository;

import com.notsecure.Appointees.entity.UserRoleModificationHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleHistoryRepository extends CrudRepository<UserRoleModificationHistory, Long> {
}
