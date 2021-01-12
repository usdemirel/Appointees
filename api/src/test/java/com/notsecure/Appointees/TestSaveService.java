package com.notsecure.Appointees;

import com.notsecure.Appointees.entity.Branch;
import com.notsecure.Appointees.entity.Company;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestSaveService {

@BeforeEach
void setUp() throws Exception {
   RestAssured.baseURI = "http://localhost";
   RestAssured.port = 8080;
}

@Test
final void testSaveService() {
   
   Company company = new Company();
   company.setId(500L);
   
   Branch branch = new Branch();
   branch.setId(300L);
   
   Map<String, Object> service = new HashMap<>();
   service.put("company", company);
   service.put("branch", branch);
   service.put("name", "FACE Therapy");
   service.put("servicePageTitle", "FACE Therapy(NEW!!)");
   service.put("description", "Each Massage Is Customized For Your Needs.");
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
   Long id = response.jsonPath().getLong("id");
   assertNotNull(id);
   
   }
   
   
}
