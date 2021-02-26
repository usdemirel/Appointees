package com.notsecure.Appointees.controller;

import com.notsecure.Appointees.entity.User;
import com.notsecure.Appointees.model.ErrorMessages;
import com.notsecure.Appointees.service.UserRoleService;
import com.notsecure.Appointees.service.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@RestController
public class UserController {
@Autowired
UserService userService;
@Autowired
UserRoleService userRoleService;

/*
   @Serkan
   1- check if both requester and requested ids are the same and the requester has one of the following permissions R_USRPF_OWN, W_USRPF_OWN, if so, then authorize.
   2- if ids are not the same, then check if requester has R_USRPF_BR or W_USRPRFL_BR permission for one of the branches the person with the requested id is registered, if so, authorize.
   3- if the first two cases are not satisfied, then look for R_USRPF_CO or W_USRPF_CO to see if the requester has company-wide priviliges.
   4- If no conditions are satisfied, throw an error.
 */
@RequestMapping("/user/{id}")
public ResponseEntity<User> getUser(@PathVariable Long id) {
   
   Map<String, Set<Long>> permissionsFinalized = userRoleService.retrieveFinalizedPermissions(id, "R_USRPF_OWN", "W_USRPF_OWN", "R_USRPF_BR", "W_USRPRFL_BR", "R_USRPF_CO", "W_USRPF_CO");
   try {
      if (!permissionsFinalized.get("permissionType").contains(0L)) {
         return ResponseEntity.status(HttpStatus.OK).body(userService.findUserById(id).get());
      } else {
         throw new ResponseStatusException(HttpStatus.FORBIDDEN, ErrorMessages.AUTHORIZATION_FAILED.getErrorMessage(), new AuthorizationServiceException("Forbidden: No permission"));
      }
   } catch (NotFoundException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
   }
}

@RequestMapping(value = "user", method = RequestMethod.POST)
public ResponseEntity<User> saveUser(@RequestBody User user) {
   System.out.println("given: " + user);
   try {
      return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
   } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.NOT_MODIFIED, e.getMessage(), e);
   }
}

   /*
   THIS IS THE SECOND BEST OPTION
   
      @RequestMapping("/user/{id}")
   public ResponseEntity<User> getUser(@PathVariable Long id){
      //1- check if both requester and requested ids are the same and the requester has one of the following permissions R_USRPF_OWN, W_USRPF_OWN, if so, then authorize.
      //2- if ids are not the same, then check if requester has R_USRPF_BR or W_USRPRFL_BR permission for one of the branches the person with the requested id is registered, if so, authorize.
      //3- if the first two cases are not satisfied, then look for R_USRPF_CO or W_USRPF_CO to see if the requester has company-wide priviliges.
      //4- If no conditions are satisfied, throw an error.
      Map<String, Set<Long>> permissionsFinalized = userRoleService.retrieveFinalizedPermissions(id,"R_USRPF_OWN","W_USRPF_OWN","R_USRPF_BR","W_USRPRFL_BR","R_USRPF_CO","W_USRPF_CO");

      //Keep this portion for now. From here..
      if ((permissionsFinalized.get("requesterUserId").stream().findFirst().get().longValue() == id && !permissionsFinalized.get("OWN").isEmpty())){
         System.out.println("has the permission to view its own profile");
      }else if(!permissionsFinalized.get("BR").isEmpty()){
         System.out.println("has the permission to view profile because the requester has the privilege to see the profiles of people working in the same branch");
      }else if(!permissionsFinalized.get("CO").isEmpty()){
         System.out.println("has the permission to view profile because the requester has the privilege to see the profiles of people working in the same company");
      }else{
         System.out.println("No permission");
      } //to here.
      
      try {
         if ((permissionsFinalized.get("requesterUserId").stream().findFirst().get().longValue() == id && !permissionsFinalized.get("OWN").isEmpty())
         || (!permissionsFinalized.get("BR").isEmpty()) || (!permissionsFinalized.get("CO").isEmpty())){
            System.out.println("Access granted!");
            return ResponseEntity.status(HttpStatus.OK).body(userService.findUserById(id).get());
         }else{
            System.out.println("No permission");
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, ErrorMessages.AUTHORIZATION_FAILED.getErrorMessage(),new AuthorizationServiceException("Forbidden: No permission"));
         }
      } catch (NotFoundException e) {
         throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage(),e);
      }
   }
    */
   
   
   /*
         @RequestMapping("/user/{id}")
   public ResponseEntity<User> getUser(@PathVariable Long id){
      System.out.println("==================================="+SecurityContextHolder.getContext().getAuthentication().getName());
      
//      System.out.println("==================================="+userRoleService.findBranchIdsUserHasRoles(1200L));
//      System.out.println("==================================="+userRoleService.findCompanyIdsUserHasRoles(1200L));
//
//
   
      //1- check if both requester and requested ids are the same and the requester has one of the following permissions R_USRPF_OWN, W_USRPF_OWN, if so, then authorize.
      //2- if ids are not the same, then check if requester has R_USRPF_BR or W_USRPRFL_BR permission for one of the branches the person with the requested id is registered, if so, authorize.
      //3- if the first two cases are not satisfied, then look for R_USRPF_CO or W_USRPF_CO to see if the requester has company-wide priviliges.
      //4- If no conditions are satisfied, throw an error.
      
//      Map<String, List<Long>> permissions = userRoleService.retrievePermissions("R_USRPF_OWN","W_USRPF_OWN","R_USRPF_BR","W_USRPRFL_BR","R_USRPF_CO","W_USRPF_CO");
//      Map<String, Set<Long>> permissionsSet = userRoleService.retrieveBasePermissions("R_USRPF_OWN","W_USRPF_OWN","R_USRPF_BR","W_USRPRFL_BR","R_USRPF_CO","W_USRPF_CO");
      Map<String, Set<Long>> permissionsFinalized = userRoleService.retrieveFinalizedPermissions(id,"R_USRPF_OWN","W_USRPF_OWN","R_USRPF_BR","W_USRPRFL_BR","R_USRPF_CO","W_USRPF_CO");
      
//      for(String key : permissions.keySet()){
//         System.out.println(key + "--"+ permissions.get(key));
//      }

//      System.out.println("BR size " + permissionsSet.get("BR").size());
//      permissionsSet.get("BR").retainAll(userRoleService.findBranchIdsUserHasRoles(id));
//      System.out.println("BR size " + permissionsSet.get("BR").size());
//
//      System.out.println("CO size " + permissionsSet.get("CO").size());
//      permissionsSet.get("CO").retainAll(userRoleService.findBranchIdsUserHasRoles(id));
//      System.out.println("CO size " + permissionsSet.get("CO").size());


//      System.out.println("\n ifs \n");
//      if ((permissionsSet.get("userId").stream().findFirst().get().longValue() == id && !permissionsSet.get("OWN").isEmpty())){
//         System.out.println("has the permission to view its own profile");
//      }else if(!permissionsSet.get("BR").isEmpty()){
//         System.out.println("has the permission to view profile because the requester has the privilege to see the profiles of people working in the same branch");
//      }else if(!permissionsSet.get("CO").isEmpty()){
//         System.out.println("has the permission to view profile because the requester has the privilege to see the profiles of people working in the same company");
//      }
      System.out.println("\n * \n");
      if ((permissionsFinalized.get("userId").stream().findFirst().get().longValue() == id && !permissionsFinalized.get("OWN").isEmpty())){
         System.out.println("has the permission to view its own profile");
      }else if(!permissionsFinalized.get("BR").isEmpty()){
         System.out.println("has the permission to view profile because the requester has the privilege to see the profiles of people working in the same branch");
      }else if(!permissionsFinalized.get("CO").isEmpty()){
         System.out.println("has the permission to view profile because the requester has the privilege to see the profiles of people working in the same company");
      }else{
         System.out.println("No permission");
      }
      System.out.println("\n end of ifs \n");

//      List<Long> branchesPermitted = new ArrayList<>();
//      branchesPermitted.addAll(permissions.get("R_USRPF_BR"));
//      branchesPermitted.addAll(permissions.get("W_USRPRFL_BR"));
//      Set<Long> branches = userRoleService.findBranchIdsUserHasRoles(1200L);
//      branches.retainAll(branchesPermitted);




//      System.out.println(permissions.get("userId").get(0) + " " + id);
//      System.out.println(permissions.containsKey("R_USRPF_OWN") || permissions.containsKey("R_USRPF_OWN"));

//      if ((permissions.get("userId").get(0).longValue() == id && (permissions.containsKey("R_USRPF_OWN") || permissions.containsKey("R_USRPF_OWN")))
//                      || (!branches.isEmpty())
//                      || (true)){
//         System.out.println("good");
//      }
   

      
   
      try {
         return ResponseEntity.status(HttpStatus.OK).body(userService.findUserById(id).get());
      } catch (NotFoundException e) {
         throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage(),e);
      }
   }
   
    */



}
