package com.notsecure.Appointees.controller;

import com.notsecure.Appointees.entity.Branch;
import com.notsecure.Appointees.entity.Company;
import com.notsecure.Appointees.entity.PublicInfo;
import com.notsecure.Appointees.model.ErrorMessages;
import com.notsecure.Appointees.service.BranchService;
import com.notsecure.Appointees.service.CompanyService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@RestController
public class CompanyController {
   
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
public ResponseEntity<PublicInfo> getPublicInfo(@PathVariable Long companyId) throws NotFoundException {
   Optional<Company> company = companyService.findCompanyById(companyId);
   if (!company.isPresent()) throw new NotFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
   return ResponseEntity.status(HttpStatus.OK).body(company.get());
}

@RequestMapping(value = "admin/{companyId}/branch", method = RequestMethod.POST)
public ResponseEntity<Branch> saveBranch(@PathVariable Long companyId, @RequestBody Branch branch) throws Exception {
   try{
      return ResponseEntity.status(HttpStatus.CREATED).body(branchService.save(branch));
   }catch(Exception e){
      throw new Exception(ErrorMessages.COULD_NOT_UPDATE_RECORD.getErrorMessage());
   }
}

@RequestMapping(value = "admin/{companyId}/branch", method = RequestMethod.OPTIONS)
public ResponseEntity<Branch> deactivateBranch(@PathVariable Long companyId, @RequestBody Branch branch) throws Exception {
   try{
//      Branch branchTemp = branchService.findBranchById(branch.getId()).get();
//      branchTemp.setActiveAccount(false);

      return ResponseEntity.status(HttpStatus.CREATED).body(branchService.save(branch));
   }catch(Exception e){
      throw new Exception(ErrorMessages.COULD_NOT_UPDATE_RECORD.getErrorMessage());
   }
}

@RequestMapping(value = "/{companyId}/branch", method = RequestMethod.PUT)
public ResponseEntity<Boolean> updateBranch(@PathVariable Long companyId, @RequestBody Branch branch) {
   
   return null;
}
   

   
   
}
