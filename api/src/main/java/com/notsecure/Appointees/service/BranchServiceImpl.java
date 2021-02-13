package com.notsecure.Appointees.service;

import com.notsecure.Appointees.entity.Branch;
import com.notsecure.Appointees.entity.WeeklyDefaultWorkHours;
import com.notsecure.Appointees.model.ErrorMessages;
import com.notsecure.Appointees.repository.BranchRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BranchServiceImpl implements BranchService {
@Autowired
BranchRepository branchRepository;

@Autowired
WeeklyDefaultWorkHoursService weeklyDefaultWorkHoursService;

@Override
public Optional<Branch> findBranchById(Long branchId) throws NotFoundException {
   Optional<Branch> branch = branchRepository.findById(branchId);
   if(!branch.isPresent()) throw new NotFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
   return branch;
}

@Override
public List<Branch> findBranchesByCompanyId(Long companyId) throws NotFoundException {
   List<Branch> branchList = branchRepository.findBranchesByCompanyId(companyId);
   if(branchList.size()==0) throw new NotFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
   return branchList;
}

@Override
public Branch save(Branch branch) throws Exception {
   if(branchRepository.existsByCompanyIdAndUniqueIdentifier(branch.getCompany().getId(), branch.getUniqueIdentifier())) throw new DuplicateKeyException(ErrorMessages.DUPLICATE_KEY_UNIQUEIDENTIFIER.getErrorMessage());

   Long id = branch.getId();
   Branch saved = branchRepository.save(branch);
   if(saved == null) throw new Exception(ErrorMessages.COULD_NOT_SAVE_RECORD.getErrorMessage());
   
   if(id==null){
      WeeklyDefaultWorkHours weeklyDefaultWorkHours = weeklyDefaultWorkHoursService.save(new WeeklyDefaultWorkHours(null, LocalDate.parse("2099-12-31"),saved.getCompany(),saved,null,null,
                      "closed","closed","closed","closed","closed","closed","closed"));
      if(weeklyDefaultWorkHours == null) throw new Exception(ErrorMessages.COULD_NOT_SAVE_RECORD.getErrorMessage()+":weekly schedule");
   }
   
   return saved;
}
}
