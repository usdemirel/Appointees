package com.notsecure.Appointees.standalone;

import com.notsecure.Appointees.AppointeesApplication;
import com.notsecure.Appointees.entity.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/*
SELECT * FROM COMPANY ;
SELECT * FROM BRANCH ;
SELECT * FROM MONTHLY_BUSINESS_WORK_DAYS ;
SELECT * FROM WEEKLY_APPOINTMENTS_PER_SERVICE_PROVIDER;
SELECT * FROM WEEKLY_CUSTOM_SERVICE_PROVIDER_SCHEDULE;
SELECT * FROM WEEKLY_DEFAULT_WORK_HOURS ;
--SELECT * FROM SERVICE ;
--SELECT * FROM SERVICE_PROVIDER ;
--SELECT * FROM CUSTOM_DAYS;
 */
public class TestCompanyInitialSetup {

private Long companyId;
private Long branchId;
private Long cleaningServiceId;
private Long pullingServiceId;
private Long serviceProviderId1;


@BeforeEach
void setUp() throws Exception {
   RestAssured.baseURI = "http://localhost";
   RestAssured.port = 8080;
}

@Test
final void testCompanyInitialSetup() {
   SpringApplication.run(AppointeesApplication.class, "");
   createCompany();
   createWeeklyCompanySchedule();
   createWeeklyCompanySchedule2();
   createBranch();
   createWeeklyBranchSchedule();
   saveCleaningService();
   savePullingService();
   saveServiceProvider();
   createWeeklySerViceProviderScheduleForCleaning();
}

private void createCompany() {
   System.out.println("createCompany *******");
   
   Address address = new Address();
   address.setAddressLine1("8132 Kirby Drive");
   address.setCity("Houston");
   address.setCountry("USA");
   address.setState("TX");
   
   AccountInfo accountInfo = new AccountInfo();
   accountInfo.setPlanType("PREMIUM");
   accountInfo.setNumberOfBookingsLeft(99);
   accountInfo.setValidUntil(LocalDate.now().plusYears(2));
   
   Map<String, Object> company = new HashMap<>();
   company.put("businessName", "Castle Dental");
   company.put("bookingPageTitle", "We're Open and Providing Safe Smiles!'");
   company.put("businessCategory", "Health");
   company.put("businessHighlights", "We're making dental more affordable!\n" +
                   "\n" +
                   "2.9% Financing on approved credit*");
   company.put("imageUrl", "https://www.castledental.com/dist/app/assets/img/logo-cd-dark.svg");
   company.put("websiteLink", "https://www.castledental.com/");
   company.put("customerSupportEmail", "support@castledental.com");
   company.put("customerSupportPhone", "(713) 797-9040");
   company.put("activeAccount", true);
   company.put("address", address);
   company.put("accountInfo", accountInfo);
   company.put("dateCreated", LocalDateTime.now());
   company.put("uniqueIdentifier", "castledental");
   
   Response response = given().
                   contentType("application/json").
                   accept("application/json").
                   body(company).
                   when().
                   post("/admin/company").
                   then().
                   statusCode(201).
                   contentType("application/json").
                   extract().
                   response();
   companyId = response.jsonPath().getLong("id");
   assertNotNull(companyId);
}

private void createWeeklyCompanySchedule() {
   System.out.println("createWeeklySchedule *******");
   Company company = new Company();
   company.setId(companyId);
   Branch branch = null;
   Service service = null;
   ServiceProvider serviceProvider = null;
   
   Map<String, Object> weeklyDefaultWorkHours = new HashMap<>();
   weeklyDefaultWorkHours.put("effectiveBy", "2022-01-29");
   weeklyDefaultWorkHours.put("company", company);
   weeklyDefaultWorkHours.put("branch", branch);
   weeklyDefaultWorkHours.put("service", service);
   weeklyDefaultWorkHours.put("serviceProvider", serviceProvider);
   weeklyDefaultWorkHours.put("sunday", "closed");
   weeklyDefaultWorkHours.put("monday", "06:00,16:00");
   weeklyDefaultWorkHours.put("tuesday", "07:00,16:00");
   weeklyDefaultWorkHours.put("wednesday", "08:00,16:00");
   weeklyDefaultWorkHours.put("thursday", "09:00,16:00");
   weeklyDefaultWorkHours.put("friday", "08:00,12:00,13:00,18:00");
   weeklyDefaultWorkHours.put("saturday", "closed");
   
   Response response = given().
                   contentType("application/json").
                   accept("application/json").
                   body(weeklyDefaultWorkHours).
                   when().
                   post("/company/weeklydefaultworkhours").
                   then().
                   statusCode(201).
                   contentType("application/json").
                   extract().
                   response();
   Long id = response.jsonPath().getLong("id");
   assertNotNull(id);
}

private void createWeeklyCompanySchedule2() {
   System.out.println("createWeeklySchedule *******");
   Company company = new Company();
   company.setId(companyId);
   Branch branch = null;
   Service service = null;
   ServiceProvider serviceProvider = null;
   
   Map<String, Object> weeklyDefaultWorkHours = new HashMap<>();
   weeklyDefaultWorkHours.put("effectiveBy", "2023-01-23");
   weeklyDefaultWorkHours.put("company", company);
   weeklyDefaultWorkHours.put("branch", branch);
   weeklyDefaultWorkHours.put("service", service);
   weeklyDefaultWorkHours.put("serviceProvider", serviceProvider);
   weeklyDefaultWorkHours.put("sunday", "closed");
   weeklyDefaultWorkHours.put("monday", "05:00,16:00");
   weeklyDefaultWorkHours.put("tuesday", "05:00,16:00");
   weeklyDefaultWorkHours.put("wednesday", "05:00,16:00");
   weeklyDefaultWorkHours.put("thursday", "05:00,16:00");
   weeklyDefaultWorkHours.put("friday", "05:00,12:00,13:00,18:00");
   weeklyDefaultWorkHours.put("saturday", "closed");
   
   Response response = given().
                   contentType("application/json").
                   accept("application/json").
                   body(weeklyDefaultWorkHours).
                   when().
                   post("/company/weeklydefaultworkhours").
                   then().
                   statusCode(201).
                   contentType("application/json").
                   extract().
                   response();
   Long id = response.jsonPath().getLong("id");
   assertNotNull(id);
}

private void createBranch() {
   Address address = new Address();
   address.setAddressLine1("9 Doger Drive");
   address.setCity("Dallas");
   address.setCountry("USA");
   address.setState("TX");
   
   AccountInfo accountInfo = new AccountInfo();
   accountInfo.setPlanType("PREMIUM");
   accountInfo.setNumberOfBookingsLeft(55);
   accountInfo.setValidUntil(LocalDate.now().plusYears(3));
   
   Company company = new Company();
   company.setId(companyId);
   
   Map<String, Object> branch = new HashMap<>();
   branch.put("businessName", "Castle Dental in Dallas");
   branch.put("uniqueIdentifier", "dallas");
   branch.put("bookingPageTitle", "Welcome to Castle Dental in Dallas!");
   branch.put("businessCategory", "Health/Beauty");
   branch.put("businessHighlights", "We're making dental more affordable! in Dallas");
   branch.put("imageUrl", "https://www.castledental.com/dist/app/assets/img/logo-cd-dark.svg");
   branch.put("websiteLink", "https://www.castledental.com/");
   branch.put("customerSupportEmail", "support@urbandayspa.com");
   branch.put("customerSupportPhone", "(982) 797-9040");
   branch.put("activeAccount", true);
   branch.put("address", address);
   branch.put("accountInfo", accountInfo);
   branch.put("zoneId", "America/Chicago");
   branch.put("company", company);
   branch.put("dateCreated", LocalDateTime.now());
   
   Response response = given().
                   contentType("application/json").
                   accept("application/json").
                   body(branch).
                   when().
                   post("/admin/branch").
                   then().
                   statusCode(201).
                   contentType("application/json").
                   extract().
                   response();
   branchId = response.jsonPath().getLong("id");
   assertNotNull(branchId);
}

private void createWeeklyBranchSchedule() {
   System.out.println("createBranchWeeklySchedule *******");
   Company company = new Company();
   company.setId(companyId);
   Branch branch = new Branch();
   branch.setId(branchId);
   Service service = null;
   ServiceProvider serviceProvider = null;
   
   Map<String, Object> weeklyDefaultWorkHours = new HashMap<>();
   weeklyDefaultWorkHours.put("effectiveBy", "2021-02-05");
   weeklyDefaultWorkHours.put("company", company);
   weeklyDefaultWorkHours.put("branch", branch);
   weeklyDefaultWorkHours.put("service", service);
   weeklyDefaultWorkHours.put("serviceProvider", serviceProvider);
   weeklyDefaultWorkHours.put("sunday", "closed");
   weeklyDefaultWorkHours.put("monday", "06:00,16:00");
   weeklyDefaultWorkHours.put("tuesday", "07:00,16:00");
   weeklyDefaultWorkHours.put("wednesday", "08:00,16:00");
   weeklyDefaultWorkHours.put("thursday", "09:00,16:00");
   weeklyDefaultWorkHours.put("friday", "08:00,12:00,13:00,18:00");
   weeklyDefaultWorkHours.put("saturday", "closed");
   
   Response response = given().
                   contentType("application/json").
                   accept("application/json").
                   body(weeklyDefaultWorkHours).
                   when().
                   post("/company/weeklydefaultworkhours").
                   then().
                   statusCode(201).
                   contentType("application/json").
                   extract().
                   response();
   Long id = response.jsonPath().getLong("id");
   assertNotNull(id);
}

final void saveCleaningService() {
   Company company = new Company();
   company.setId(companyId);
   
   Branch branch = new Branch();
   branch.setId(branchId);
   
   Map<String, Object> service = new HashMap<>();
   service.put("company", company);
   service.put("branch", branch);
   service.put("name", "Teeth Whitening");
   service.put("servicePageTitle", "Your Teeth are As * As Your Future (NEW!!)");
   service.put("description", "Customized Care For Your Needs.");
   service.put("serviceAvailableOnBP", true);
   service.put("price", 100.0);
   service.put("priceAvailableOnBP", true);
   service.put("imageLink", null);
   service.put("duration", 30);
   service.put("bufferTime", 30);
   service.put("durationVisibleOnBP", false);
   service.put("serviceInfoPublicToAllBranches", true);
   service.put("allowedCancellationTimeWindowPriorToAppointment", 50);
   service.put("allowedDaysInAdvanceAppointmentBookings", 90);
   
   
   Response response = given().
                   contentType("application/json").
                   accept("application/json").
                   body(service).
                   when().
                   post("/admin/service").
                   then().
                   statusCode(201).
                   contentType("application/json").
                   extract().
                   response();
   cleaningServiceId = response.jsonPath().getLong("id");
   assertNotNull(cleaningServiceId);
   
}

final void savePullingService() {
   Company company = new Company();
   company.setId(companyId);
   
   Branch branch = new Branch();
   branch.setId(branchId);
   
   Map<String, Object> service = new HashMap<>();
   service.put("company", company);
   service.put("branch", branch);
   service.put("name", "Teeth Pulling");
   service.put("servicePageTitle", "Your Teeth are GONE (NEW!!)");
   service.put("description", "Customized Care For Your Needs.");
   service.put("serviceAvailableOnBP", true);
   service.put("price", 50.0);
   service.put("priceAvailableOnBP", true);
   service.put("imageLink", null);
   service.put("duration", 45);
   service.put("bufferTime", 15);
   service.put("durationVisibleOnBP", false);
   service.put("serviceInfoPublicToAllBranches", true);
   service.put("allowedCancellationTimeWindowPriorToAppointment", 100);
   service.put("allowedDaysInAdvanceAppointmentBookings", 90);
   
   
   Response response = given().
                   contentType("application/json").
                   accept("application/json").
                   body(service).
                   when().
                   post("/admin/service").
                   then().
                   statusCode(201).
                   contentType("application/json").
                   extract().
                   response();
   pullingServiceId = response.jsonPath().getLong("id");
   assertNotNull(pullingServiceId);
}

final void saveServiceProvider() {
   
   Company company = new Company();
   company.setId(companyId);
   
   Branch branch = new Branch();
   branch.setId(branchId);
   
   Service service1 = new Service();
   service1.setId(cleaningServiceId);
   Service service2 = new Service();
   service2.setId(pullingServiceId);
   
   List<Service> serviceList = new ArrayList<>();
   serviceList.add(service1);
   serviceList.add(service2);
   
   User user = new User();
   user.setId(1206L);
   
   Map<String, Object> serviceProvider = new HashMap<>();
   serviceProvider.put("company", company);
   serviceProvider.put("branch", branch);
   serviceProvider.put("user", user);
   serviceProvider.put("services", serviceList);
   serviceProvider.put("serviceProviderAvailableOnBP", true);
   
   Response response = given().
                   contentType("application/json").
                   accept("application/json").
                   body(serviceProvider).
                   when().
                   post("/admin/serviceprovider").
                   then().
                   statusCode(201).
                   contentType("application/json").
                   extract().
                   response();
   serviceProviderId1 = response.jsonPath().getLong("id");
   assertNotNull(serviceProviderId1);
}

private void createWeeklySerViceProviderScheduleForCleaning() {
   System.out.println("createWeeklySerViceProviderScheduleForCleaning *******");
   Company company = new Company();
   company.setId(companyId);
   Branch branch = new Branch();
   branch.setId(branchId);
   Service service = new Service();
   service.setId(cleaningServiceId);
   service.setDuration(30);
   service.setBufferTime(10);
   ServiceProvider serviceProvider = new ServiceProvider();
   serviceProvider.setId(serviceProviderId1);
   
   Map<String, Object> weeklyDefaultWorkHours = new HashMap<>();
   weeklyDefaultWorkHours.put("effectiveBy", "2021-02-20");
   weeklyDefaultWorkHours.put("company", company);
   weeklyDefaultWorkHours.put("branch", branch);
   weeklyDefaultWorkHours.put("service", service);
   weeklyDefaultWorkHours.put("serviceProvider", serviceProvider);
   weeklyDefaultWorkHours.put("sunday", "closed");
   weeklyDefaultWorkHours.put("monday", "06:00,16:00");
   weeklyDefaultWorkHours.put("tuesday", "07:00,16:00");
   weeklyDefaultWorkHours.put("wednesday", "08:00,16:00");
   weeklyDefaultWorkHours.put("thursday", "09:00,16:00");
   weeklyDefaultWorkHours.put("friday", "08:00,12:00,13:00,18:00");
   weeklyDefaultWorkHours.put("saturday", "closed");
   
   Response response = given().
                   contentType("application/json").
                   accept("application/json").
                   body(weeklyDefaultWorkHours).
                   when().
                   post("/company/weeklydefaultworkhours").
                   then().
                   statusCode(201).
                   contentType("application/json").
                   extract().
                   response();
   Long id = response.jsonPath().getLong("id");
   assertNotNull(id);
}

}