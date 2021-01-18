package com.notsecure.Appointees.service;

import com.notsecure.Appointees.entity.User;
import com.notsecure.Appointees.model.ErrorMessages;
import com.notsecure.Appointees.repository.UserRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
   @Autowired
   UserRepository userRepository;
   
@Override
public Optional<User> findUserById(Long Id) throws NotFoundException {
   Optional<User> user = userRepository.findById(Id);
   if(user.isEmpty()) throw new NotFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
   return Optional.empty();
}

@Override
public User save(User user) throws Exception {
   User saved = userRepository.save(user);
   if(saved==null) throw new Exception(ErrorMessages.COULD_NOT_SAVE_RECORD.getErrorMessage());
   return saved;
}
}
