package com.notsecure.Appointees.service;

import com.notsecure.Appointees.entity.Branch;
import com.notsecure.Appointees.entity.Company;
import com.notsecure.Appointees.entity.WeeklyDefaultWorkHours;
import com.notsecure.Appointees.model.ErrorMessages;
import com.notsecure.Appointees.repository.BranchRepository;
import com.notsecure.Appointees.repository.CompanyRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService{

@Autowired
CompanyRepository companyRepository;

@Autowired
BranchRepository branchRepository;

@Autowired
WeeklyDefaultWorkHoursService weeklyDefaultWorkHoursService;


@Override
public Optional<Company> findCompanyById(Long companyId) throws NotFoundException {
 Optional<Company> company = companyRepository.findById(companyId);
 if (!company.isPresent()) throw new NotFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
 return company;
}

@Override
public Optional<Company> findCompanyByName(String name) {
 return companyRepository.findCompanyByBusinessName(name);
}

@Override
@Transactional
public Company save(Company company) throws Exception {
 Long id = company.getId();
 if(companyRepository.existsByUniqueIdentifier(company.getUniqueIdentifier())) throw new DuplicateKeyException(ErrorMessages.DUPLICATE_KEY_UNIQUEIDENTIFIER.getErrorMessage());
 Company saved = companyRepository.save(company);
 if(saved == null) throw new Exception(ErrorMessages.COULD_NOT_SAVE_RECORD.getErrorMessage());
if(id==null){
 WeeklyDefaultWorkHours weeklyDefaultWorkHours = weeklyDefaultWorkHoursService.save(new WeeklyDefaultWorkHours(null, LocalDate.parse("2099-12-31"),saved,null,null,null,
                 "closed","closed","closed","closed","closed","closed","closed"));
 if(weeklyDefaultWorkHours == null) throw new Exception(ErrorMessages.COULD_NOT_SAVE_RECORD.getErrorMessage()+":weekly schedule");
}
 
 return saved;
}

@Override
public boolean existsByCompanyUniqueIdentifier(String uniqueIdentifier) {
 return companyRepository.existsByUniqueIdentifier(uniqueIdentifier);
}

}
