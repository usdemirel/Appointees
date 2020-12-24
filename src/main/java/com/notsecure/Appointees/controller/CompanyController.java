package com.notsecure.Appointees.controller;

import com.notsecure.Appointees.entity.Branch;
import com.notsecure.Appointees.entity.Company;
import com.notsecure.Appointees.model.ErrorMessages;
import com.notsecure.Appointees.service.BranchService;
import com.notsecure.Appointees.service.CompanyService;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;


@RestController
public class CompanyController {
   
   Logger logger = LoggerFactory.getLogger(CompanyController.class);
   
   @Autowired
   CompanyService companyService;
   
   @Autowired
   BranchService branchService;

//https://www.baeldung.com/spring-response-status-exception
//https://howtodoinjava.com/best-practices/java-exception-handling-best-practices/
@RequestMapping("public/company/{companyId}/branch/all")
public ResponseEntity<List<Branch>> getBranches(@PathVariable Long companyId) {
   try {
      return ResponseEntity.status(HttpStatus.OK).body(branchService.findBranchesByCompanyId(companyId));
   } catch (NotFoundException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
   }
}

@RequestMapping("/public/{companyId}/public-info")
public ResponseEntity<Company> getPublicInfo(@PathVariable Long companyId){
   try {
      return ResponseEntity.status(HttpStatus.OK).body(companyService.findCompanyById(companyId).get());
   } catch (NotFoundException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(),e);
   }
}

@RequestMapping(value = "admin/company", method = RequestMethod.POST)
public ResponseEntity<Company> saveCompany(@RequestBody Company company) {
   try{
      logger.error("New company creation attempt: " + company.toString());
      return ResponseEntity.status(HttpStatus.CREATED).body(companyService.save(company));
   }catch(Exception e){
      throw new ResponseStatusException(HttpStatus.NOT_MODIFIED,e.getMessage(),e);
   }
}

@RequestMapping(value = "admin/company", method = RequestMethod.PUT)
public ResponseEntity<Company> updateCompany(@RequestBody Company company) {
   try{
      if (company.getId() == null) throw new NotFoundException(ErrorMessages.COULD_NOT_UPDATE_RECORD.getErrorMessage() + ". Id not found.");
      return ResponseEntity.status(HttpStatus.CREATED).body(companyService.save(company));
   }catch(Exception e){
      throw new ResponseStatusException(HttpStatus.NOT_MODIFIED,e.getMessage(),e);
   }
}

@RequestMapping(value = "admin/{companyId}/branch", method = RequestMethod.POST)
public ResponseEntity<Branch> saveBranch(@PathVariable Long companyId, @RequestBody Branch branch){
      logger.info("New branch creation attempt" + branch.toString());
   try {
      return ResponseEntity.status(HttpStatus.CREATED).body(branchService.save(branch));
   } catch (Exception e) {
      logger.info("New branch creation attempt" + branch.toString());
      throw new ResponseStatusException(HttpStatus.NOT_MODIFIED,e.getMessage(),e);
   }
}

@RequestMapping(value = "admin/{companyId}/branch", method = RequestMethod.OPTIONS)
public ResponseEntity<Branch> deactivateBranch(@PathVariable Long companyId, @RequestBody Branch branch) {
   try{
      // I could't use the branch directly, because It was causing the lose of Company column info.
      return ResponseEntity.status(HttpStatus.CREATED).body(branchService.save(branchService.findBranchById(branch.getId()).get().setActiveAccountFalse()));
   }catch(Exception e){
      throw new ResponseStatusException(HttpStatus.NOT_MODIFIED,e.getMessage(),e);
   }
}

@RequestMapping(value = "admin/{companyId}/{branchId}", method = RequestMethod.OPTIONS)
public ResponseEntity<Branch> deactivateBranch(@PathVariable Long companyId, @PathVariable Long branchId) {
   try{
      return ResponseEntity.status(HttpStatus.OK).body(branchService.save(branchService.findBranchById(branchId).get().setActiveAccountFalse()));
   }catch(Exception e){
      throw new ResponseStatusException(HttpStatus.NOT_MODIFIED,e.getMessage(),e);
   }
}

@RequestMapping(value = "/{companyId}/branch", method = RequestMethod.PUT)
public ResponseEntity<Branch> updateBranch(@PathVariable Long companyId, @RequestBody Branch branch) throws Exception {
   try{
      return ResponseEntity.status(HttpStatus.CREATED).body(branchService.save(branch)); // must have company info attached.
   }catch(Exception e){
      throw new ResponseStatusException(HttpStatus.NOT_MODIFIED,e.getMessage(),e);
   }
}
}
