package com.notsecure.Appointees;

import com.notsecure.Appointees.entity.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestSaveWeeklyDefaultWorkHours {

@BeforeEach
void setUp() throws Exception {
   RestAssured.baseURI = "http://localhost";
   RestAssured.port = 8080;
}

@Test
final void testSaveWeeklyDefaultWorkHours() {
   
   Company company = new Company();
   company.setId(500L);
   
   Branch branch = new Branch();
   branch.setId(300L);
   
   Service service = new Service();
   service.setId(1000L);
   service.setDuration(35);
   service.setBufferTime(5);
   
   ServiceProvider serviceProvider = new ServiceProvider();
   serviceProvider.setId(1100L);
   
   Map<String, Object> weeklyDefaultWorkHours = new HashMap<>();
   weeklyDefaultWorkHours.put("effectiveBy", "2021-01-29");
   weeklyDefaultWorkHours.put("company", company);
   weeklyDefaultWorkHours.put("branch", branch);
   weeklyDefaultWorkHours.put("service", service);
   weeklyDefaultWorkHours.put("serviceProvider", serviceProvider);
   weeklyDefaultWorkHours.put("sunday", "closed");
   weeklyDefaultWorkHours.put("monday", "07:59,11:11");
   weeklyDefaultWorkHours.put("tuesday", "10:00,12:00");
   weeklyDefaultWorkHours.put("wednesday", "08:00,12:00");
   weeklyDefaultWorkHours.put("thursday", "11:01,15:05");
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
