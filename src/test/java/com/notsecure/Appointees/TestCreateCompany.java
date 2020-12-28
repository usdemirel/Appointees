package com.notsecure.Appointees;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
   
   Map<String, Object> company = new HashMap<>();
   company.put("businessName", "Urban Spa Day2");
   company.put("bookingPageTitle", "Welcome to Urban Day Spa!");
   company.put("businessCategory", "Beauty/Health");
   company.put("businessHighlights", "Urban Day Spa, with 2 locations in the Greater Houston Area");
   company.put("imageUrl", "https://clients.mindbodyonline.com/studios/UrbanDaySpaSpringTX/logo.gif");
   company.put("websiteLink", "https://urbandayspa.com/");
   company.put("customerSupportEmail", "support@urbandayspa.com");
   company.put("customerSupportPhone", "832-698-1544");
   company.put("activeAccount", true);
   company.put("address", null);
   company.put("accountInfo", null);
   company.put("dateCreated", null);
   
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
