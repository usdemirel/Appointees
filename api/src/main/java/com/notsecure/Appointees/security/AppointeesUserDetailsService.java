package com.notsecure.Appointees.security;

import com.notsecure.Appointees.model.ErrorMessages;
import com.notsecure.Appointees.service.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AppointeesUserDetailsService implements UserDetailsService {
   @Autowired
UserService userService;

@Override
public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
   //TODO: pull the user from database
   com.notsecure.Appointees.entity.User user;
   try {
      user = userService.findUserByEmail(email).get();
   } catch (NotFoundException e) {
      throw new UsernameNotFoundException(ErrorMessages.EMAIL_ADDRESS_NOT_VERIFIED.getErrorMessage());
   }
   System.out.println(user + " __________________");
   return new User(user.getEmail(), user.getPassword(), new ArrayList<>()); //TODO: the last parameter is the collection of authorities //As a second thought, this may not be necessary
}
}
