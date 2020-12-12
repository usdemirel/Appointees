package com.notsecure.Appointees.controller;

import com.notsecure.Appointees.repository.UserRepository;
import com.notsecure.Appointees.repository.UserRoleHistoryRepository;
import com.notsecure.Appointees.service.WeeklyWorkHoursService;
import org.hibernate.resource.transaction.NullSynchronizationException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;

@RestController
public class Welcome {

Logger logger = LoggerFactory.getLogger(Welcome.class);


@Autowired
WeeklyWorkHoursService weeklyWorkHoursService;

@Autowired
UserRoleHistoryRepository userRoleHistoryRepository;

@Autowired
UserRepository userRepository;

//@Autowired
//UserRoleRepository userRoleRepository;

@RequestMapping("/welcome")
public ResponseEntity<String> welcome() {
//  System.out.println(weeklyWorkHoursService.findById(301L).get().getSaturday());
//  User userAdmin = new User(1L,"userAdmin");
//  userRepository.save(userAdmin);
//    UserRole userRoleAdmin = new UserRole("admin",userRepository.findById(1L).get()); //self
//    userRoleRepository.save(userRoleAdmin);
//    UserRoleHistory userRoleHistoryAdmin = new UserRoleHistory(userRoleRepository.findById(2L).get(),"additional info");
//    userRoleHistoryRepository.save(userRoleHistoryAdmin);
//    UserRoleHistory userRoleHistoryAdmin2 = new UserRoleHistory(userRoleRepository.findById(2L).get(),"BBBadditional info");
//    userRoleHistoryRepository.save(userRoleHistoryAdmin2);
//    userRoleHistoryRepository.save(userRoleHistoryAdmin);
   
   
   return ResponseEntity.status(HttpStatus.CREATED).body("welcomed");
}

@RequestMapping("/{company}/{day}")
public String getDay(@PathVariable String company, @PathVariable Long day) throws Exception {
   System.out.println("/{company}/{day}");
   logger.error("Null pointer error occured");
   
   if (day != 31)
      throw new NullSynchronizationException("sync");
//   throw new IllegalAccessException("illegal..");
//     throw new HttpMediaTypeNotSupportedException("media bisey");
//   throw new HttpRequestMethodNotSupportedException("http meth not supp..");
//  throw new CompanyServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
//  throw new Exception(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
   return null;
}


}
