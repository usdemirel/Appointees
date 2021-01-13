package com.notsecure.Appointees;

import com.notsecure.Appointees.entity.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DuplicateKeyException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
   Long id = response.jsonPath().getLong("id");
   assertNotNull(id);
   }


@Test
final void testSaveServiceProvider_Duplicate() {
   
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
   
   Map<String, Object> serviceProvider = new HashMap<>();
   serviceProvider.put("company", company);
   serviceProvider.put("branch", branch);
   serviceProvider.put("user", user);
   serviceProvider.put("services", serviceList);
   serviceProvider.put("serviceProviderAvailableOnBP", true);
   
   given().
                   contentType("application/json").
                   accept("application/json").
                   body(serviceProvider).
                   when().
                   post("/admin/serviceprovider").
                   then().
                   statusCode(409).
                   contentType("application/json").
                   extract().
                   response();
}

}
