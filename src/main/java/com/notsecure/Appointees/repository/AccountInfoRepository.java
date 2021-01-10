package com.notsecure.Appointees.repository;

import com.notsecure.Appointees.entity.AccountInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountInfoRepository extends CrudRepository<AccountInfo,Long> {
}
