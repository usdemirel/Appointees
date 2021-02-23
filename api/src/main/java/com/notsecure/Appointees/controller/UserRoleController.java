package com.notsecure.Appointees.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.notsecure.Appointees.entity.User;
import com.notsecure.Appointees.entity.UserRole;
import com.notsecure.Appointees.service.UserRoleService;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class UserRoleController {
   @Autowired
   UserRoleService userRoleService;

   @RequestMapping("/userroles")
ResponseEntity<List<UserRole>> retrieveAllUserRoles(){
      return ResponseEntity.status(HttpStatus.OK).body(userRoleService.findAll());
   }

@RequestMapping(value="/userrole", method = RequestMethod.POST, consumes={"application/json"})
public ResponseEntity<UserRole> saveUserRole(@RequestBody UserRole userRole) throws JsonProcessingException {
   System.out.println("given: " + userRole);
   try {
      return ResponseEntity.status(HttpStatus.CREATED).body(userRoleService.save(userRole));
   } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.NOT_MODIFIED,e.getMessage(),e);
   }
}




}
