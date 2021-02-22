package com.notsecure.Appointees.service;

import com.notsecure.Appointees.entity.User;
import javassist.NotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface UserService {
Optional<User> findUserById(Long Id) throws NotFoundException;
Optional<User> findUserByEmail(String email) throws NotFoundException;
User save(User user) throws Exception;


}
