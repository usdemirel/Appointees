package com.notsecure.Appointees;

import com.notsecure.Appointees.entity.AccountInfo;
import com.notsecure.Appointees.entity.Address;
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

public class TestCreateCompany {

@BeforeEach
void setUp() throws Exception {
   RestAssured.baseURI = "http://localhost";
   RestAssured.port = 8080;
}

@Test
final void testCreateCompany() {
   
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
   Long id = response.jsonPath().getLong("id");
   assertNotNull(id);
   
   }
   
   
}
