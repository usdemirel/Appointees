package com.notsecure.Appointees;

import com.notsecure.Appointees.entity.AccountInfo;
import com.notsecure.Appointees.entity.Address;
import com.notsecure.Appointees.entity.Company;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestCreateBranch {

@BeforeEach
void setUp() throws Exception {
   RestAssured.baseURI = "http://localhost";
   RestAssured.port = 8080;
}

@Test
final void testCreateBranch() {
   
   Address address = new Address();
   address.setAddressLine1("932 Dally Drive");
   address.setCity("Dallas");
   address.setCountry("USA");
   address.setState("TX");
   
   AccountInfo accountInfo = new AccountInfo();
   accountInfo.setPlanType("PREMIUM");
   accountInfo.setNumberOfBookingsLeft(55);
   accountInfo.setValidUntil(LocalDate.now().plusYears(3));
   
   Company company = new Company();
   company.setId(500L);
   
   Map<String, Object> branch = new HashMap<>();
   branch.put("businessName", "Urban Day Spa in Dallas");
   branch.put("bookingPageTitle", "Welcome to Urban Day Spa in Dallas!");
   branch.put("businessCategory", "Health/Beauty");
   branch.put("businessHighlights", "All sort of care provided at our Dallas Branch");
   branch.put("imageUrl", "https://clients.mindbodyonline.com/studios/UrbanDaySpaSpringTX/logo.gif");
   branch.put("websiteLink", "https://urbandayspa.com/");
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
   Long id = response.jsonPath().getLong("id");
   assertNotNull(id);
   }
}
