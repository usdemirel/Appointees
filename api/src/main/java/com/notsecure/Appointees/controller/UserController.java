package com.notsecure.Appointees.controller;

import com.notsecure.Appointees.entity.User;
import com.notsecure.Appointees.service.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class UserController {
   @Autowired
   UserService userService;
   
   @RequestMapping("/user/{id}")
   public ResponseEntity<User> getUser(@PathVariable Long id){
      try {
         return ResponseEntity.status(HttpStatus.OK).body(userService.findUserById(id).get());
      } catch (NotFoundException e) {
         throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage(),e);
      }
   }
   
   @RequestMapping(value="/user", method = RequestMethod.POST)
   public ResponseEntity<User> saveUser(@RequestBody User user){
      try {
         return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
      } catch (Exception e) {
         throw new ResponseStatusException(HttpStatus.NOT_MODIFIED,e.getMessage(),e);
      }
   }
   
}
