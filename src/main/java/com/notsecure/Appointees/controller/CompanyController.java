package com.notsecure.Appointees.controller;

import com.notsecure.Appointees.entity.Branch;
import com.notsecure.Appointees.entity.Company;
import com.notsecure.Appointees.entity.PublicInfo;
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
import java.util.List;
import java.util.Optional;


@RestController
public class CompanyController {
   
   Logger logger = LoggerFactory.getLogger(CompanyController.class);
   
   
   @Autowired
   CompanyService companyService;
   
   @Autowired
   BranchService branchService;
   
@RequestMapping("public/company/{companyId}")
public ResponseEntity<List<Branch>> getBranches(@PathVariable Long companyId) throws NotFoundException {
   List<Branch> branches = branchService.findBranchesByCompanyId(companyId);
   if (branches.size() ==0) throw new NotFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
   return ResponseEntity.status(HttpStatus.OK).body(branches);
}

@RequestMapping("/public/{companyId}/public-info")
public ResponseEntity<Company> getPublicInfo(@PathVariable Long companyId) throws NotFoundException {
   Optional<Company> company = companyService.findCompanyById(companyId);
   if (!company.isPresent()) throw new NotFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
   return ResponseEntity.status(HttpStatus.OK).body(company.get());
}

@RequestMapping(value = "admin/company", method = RequestMethod.POST)
public ResponseEntity<Company> saveCompany(@RequestBody Company company) throws Exception {
   try{
      logger.info("New company creation attempt" + company.toString());
      return ResponseEntity.status(HttpStatus.CREATED).body(companyService.save(company));
   }catch(Exception e){
      throw new Exception(ErrorMessages.COULD_NOT_SAVE_RECORD.getErrorMessage());
   }
}

@RequestMapping(value = "admin/company", method = RequestMethod.PUT)
public ResponseEntity<Company> updateCompany(@RequestBody Company company) throws Exception {
   try{
      if (company.getId() == null) throw new NotFoundException(ErrorMessages.COULD_NOT_UPDATE_RECORD.getErrorMessage() + ". Id not found.");
      return ResponseEntity.status(HttpStatus.CREATED).body(companyService.save(company));
   }catch(Exception e){
      throw new Exception(ErrorMessages.COULD_NOT_SAVE_RECORD.getErrorMessage());
   }
}

@RequestMapping(value = "admin/{companyId}/branch", method = RequestMethod.POST)
public ResponseEntity<Branch> saveBranch(@PathVariable Long companyId, @RequestBody Branch branch) throws Exception {
   try{
      logger.info("New branch creation attempt" + branch.toString());
      return ResponseEntity.status(HttpStatus.CREATED).body(branchService.save(branch));
   }catch(Exception e){
      throw new Exception(ErrorMessages.COULD_NOT_SAVE_RECORD.getErrorMessage());
   }
}

@RequestMapping(value = "admin/{companyId}/branch", method = RequestMethod.OPTIONS)
public ResponseEntity<Branch> deactivateBranch(@PathVariable Long companyId, @RequestBody Branch branch) throws Exception {
   try{
      // I could't use the branch directly, because It was causing the lose of Company column info.
      return ResponseEntity.status(HttpStatus.CREATED).body(branchService.save(branchService.findBranchById(branch.getId()).get().setActiveAccountFalse()));
   }catch(Exception e){
      throw new Exception(ErrorMessages.COULD_NOT_UPDATE_RECORD.getErrorMessage());
   }
}

@RequestMapping(value = "admin/{companyId}/{branchId}", method = RequestMethod.OPTIONS)
public ResponseEntity<Branch> deactivateBranch(@PathVariable Long companyId, @PathVariable Long branchId) throws Exception {
   try{
      return ResponseEntity.status(HttpStatus.CREATED).body(branchService.save(branchService.findBranchById(branchId).get().setActiveAccountFalse()));
   }catch(Exception e){
      throw new Exception(ErrorMessages.COULD_NOT_UPDATE_RECORD.getErrorMessage());
   }
}

@RequestMapping(value = "/{companyId}/branch", method = RequestMethod.PUT)
public ResponseEntity<Branch> updateBranch(@PathVariable Long companyId, @RequestBody Branch branch) throws Exception {
   try{
      return ResponseEntity.status(HttpStatus.CREATED).body(branchService.save(branch)); // must have company info attached.
   }catch(Exception e){
      throw new Exception(ErrorMessages.COULD_NOT_UPDATE_RECORD.getErrorMessage());
   }
}
   

   
   
}
