package com.notsecure.Appointees.service;

import com.notsecure.Appointees.entity.User;
import javassist.NotFoundException;

import java.util.Optional;

public interface UserService {
   Optional<User> findUserById(Long Id) throws NotFoundException;
   User save(User user) throws Exception;
}
