package com.notsecure.Appointees;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppointeesApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppointeesApplication.class, args);
		System.out.println("jwt TOKEN: " + "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzYWxpaEB1cmJhbnNwYWRheS5jb20iLCJleHAiOjE2MTU2NDg2NDIsImlhdCI6MTYxNDAwODM4MH0.a0XxahwL-Mt7ZNlfEQLbHsEILzd1xWgOYaWpCIITPxU");
	}
	
	//TODO Create company
	//TODO Create branches
	//TODO BranchWeekly Schedule
	//TODO Create services
	//TODO Create service provider
	//TODO Create ServiceProviderWeeklySchedule
	//TODO Create Appointment
	//TODO

}
