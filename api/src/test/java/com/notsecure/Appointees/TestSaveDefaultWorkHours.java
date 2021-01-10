package com.notsecure.Appointees;

import com.notsecure.Appointees.entity.Address;
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

public class TestSaveDefaultWorkHours {

@BeforeEach
void setUp() throws Exception {
   RestAssured.baseURI = "http://localhost";
   RestAssured.port = 8080;
}

@Test
final void testCreateCompany() {
   
   Company company = new Company();
   company.setId(500L);
   
   Branch branch = new Branch();
   branch.setId(300L);
   
   Map<String, Object> weeklyDefaultWorkHours = new HashMap<>();
   weeklyDefaultWorkHours.put("effectiveBy", "2019-05-19");
   weeklyDefaultWorkHours.put("company", company);
   weeklyDefaultWorkHours.put("branch", branch);
   weeklyDefaultWorkHours.put("service", null);
   weeklyDefaultWorkHours.put("serviceProvider", null);
   weeklyDefaultWorkHours.put("sunday", "11:01,15:05");
   weeklyDefaultWorkHours.put("monday", "11:01,15:05");
   weeklyDefaultWorkHours.put("tuesday", "11:01,15:05");
   weeklyDefaultWorkHours.put("wednesday", "11:01,15:05");
   weeklyDefaultWorkHours.put("thursday", "11:01,15:05");
   weeklyDefaultWorkHours.put("friday", "11:01,15:05");
   weeklyDefaultWorkHours.put("saturday", "11:01,15:05");
   
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
