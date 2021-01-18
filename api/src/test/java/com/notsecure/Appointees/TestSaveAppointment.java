package com.notsecure.Appointees;

import com.notsecure.Appointees.entity.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestSaveAppointment {

@BeforeEach
void setUp() throws Exception {
   RestAssured.baseURI = "http://localhost";
   RestAssured.port = 8080;
}

@Test
final void testSaveAppointment() {
   
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
   
   Client client = new Client();
   client.setName("ALI");
   client.setEmail("CLIENT@GMAIL.COM");
   client.setPhone("5049877856");
   
   Map<String, Object> weeklyDefaultWorkHours = new HashMap<>();
   weeklyDefaultWorkHours.put("dateCreated", "2021-01-18T00:00:00");
   weeklyDefaultWorkHours.put("appointmentDateTime", "2021-01-10T11:08:00");
   weeklyDefaultWorkHours.put("bookingConfirmationNumber", "FERDSF5SDXT");
   weeklyDefaultWorkHours.put("cancelled", false);
   weeklyDefaultWorkHours.put("client",client);
   weeklyDefaultWorkHours.put("company",company);
   weeklyDefaultWorkHours.put("branch",branch);
   weeklyDefaultWorkHours.put("serviceProvider",serviceProvider);
   weeklyDefaultWorkHours.put("service",service);

   Response response = given().
                   contentType("application/json").
                   accept("application/json").
                   body(weeklyDefaultWorkHours).
                   when().
                   post("/appointment").
                   then().
                   statusCode(201).
                   contentType("application/json").
                   extract().
                   response();
   Long id = response.jsonPath().getLong("id");
   assertNotNull(id);
   }
}
