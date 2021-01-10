package com.notsecure.Appointees.controller;

import com.notsecure.Appointees.entity.Branch;
import com.notsecure.Appointees.service.BranchService;
import com.notsecure.Appointees.service.CompanyService;
import javassist.NotFoundException;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class CompanyControllerTest {
List<Branch> branchList = new ArrayList<Branch>();

@InjectMocks
CompanyController companyController;
@Mock
CompanyService companyService;
@Mock
BranchService branchService;

@BeforeEach
void setUp() {
   MockitoAnnotations.initMocks(this);
   loadBranchList();
}

private void loadBranchList() {
   Branch branch1 = new Branch();
   branch1.setId(300L);
   branch1.setZoneId("Europe/Istanbul");
   
   Branch branch2 = new Branch();
   branch2.setId(301L);
   branch2.setZoneId("Europe/Germany");
   
   branchList.add(branch1);
   branchList.add(branch2);
}

@AfterEach
void tearDown() {
}

@SneakyThrows
@Test
void getBranches() {
   when(branchService.findBranchesByCompanyId(anyLong())).thenReturn(branchList);
   List<Branch> branches = (List<Branch>) companyController.getBranches(500L).getBody();
   assertEquals(branches,branchList);
}

@Test
void getPublicInfo() {
}

@Test
void saveCompany() {
}

@Test
void updateCompany() {
}

@Test
void saveBranch() {
}

@Test
void deactivateBranch() {
}

@Test
void testDeactivateBranch() {
}

@Test
void updateBranch() {
}
}