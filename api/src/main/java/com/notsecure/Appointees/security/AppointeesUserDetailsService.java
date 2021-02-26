package com.notsecure.Appointees.security;

import com.notsecure.Appointees.model.ErrorMessages;
import com.notsecure.Appointees.service.UserRoleService;
import com.notsecure.Appointees.service.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class AppointeesUserDetailsService implements UserDetailsService {
@Autowired
UserService userService;

@Autowired
UserRoleService userRoleService;

@Override
public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
   //TODO: pull the user from database
   com.notsecure.Appointees.entity.User user;
   
//   final List<GrantedAuthority> roles = new ArrayList<>();
   try {
      user = userService.findUserByEmail(email).get(); //9
      
//      userRoleService.findUserRolesByUserIdAndActiveIsTrue(1200L).forEach(userRole -> roles.add(new GrantedAuthority() {
//         @Override
//         public String getAuthority() {
//            return userRole.getCompany().getId() + ";" + userRole.getBranch() != null ? String.valueOf(userRole.getBranch().getId()) : "null" + ";" + userRole.getRole().getPermissions();
//         }
//      }));
   } catch (NotFoundException e) {
      throw new UsernameNotFoundException(ErrorMessages.EMAIL_ADDRESS_NOT_VERIFIED.getErrorMessage());
   }
   return new User(user.getEmail(), user.getPassword(), new ArrayList<>()); //TODO: the last parameter is the collection of authorities //As a second thought, this may not be necessary
}
}
