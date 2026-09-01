package com.dev.workshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entry point for the Workshop Spring Boot application.
 * 
 * @author Dev
 * @version 1.0
 */
@SpringBootApplication
public class WorkshopSpringbootApplication {

	/**
	 * Main method to bootstrap the Spring Boot application.
	 * 
	 * @param args command-line arguments passed during startup
	 */
	public static void main(String[] args) {
		SpringApplication.run(WorkshopSpringbootApplication.class, args);
	}

}
