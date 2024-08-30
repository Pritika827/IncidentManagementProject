package com.transline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.transline")
public class IncidentManagementProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(IncidentManagementProjectApplication.class, args);
		System.out.println("Application is running on port no. 8080..............");
	}

}
