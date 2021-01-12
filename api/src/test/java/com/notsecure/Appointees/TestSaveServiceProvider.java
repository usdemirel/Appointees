package com.notsecure.Appointees;

import com.notsecure.Appointees.entity.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestSaveServiceProvider {

@BeforeEach
void setUp() throws Exception {
   RestAssured.baseURI = "http://localhost";
   RestAssured.port = 8080;
}

@Test
final void testSaveServiceProvider() {
   
   Company company = new Company();
   company.setId(500L);
   
   Branch branch = new Branch();
   branch.setId(300L);
   
   Service service1 = new Service();
   service1.setId(1000L);
   Service service2 = new Service();
   service2.setId(1001L);
   
   List<Service> serviceList = new ArrayList<>();
   serviceList.add(service1);
   serviceList.add(service2);
   
   User user = new User();
   user.setId(1205L);
//   user.setAddress(new Address());
//   user.setDateCreated(LocalDateTime.now());
//   user.setEmail("sd@gmail.com");
//   user.setDisplayName("NalBand SER");
//   user.setFirstName("Sir");
//   user.setLastName("Can");
//   user.setPassword("password is password");
//   user.setPhoneNumber("987997798987");
   
   
   Map<String, Object> serviceProvider = new HashMap<>();
//   serviceProvider.put("id",3);
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
   Long id = response.jsonPath().getLong("id");
   assertNotNull(id);
   
   }
   
   
}
