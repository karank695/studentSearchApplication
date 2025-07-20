package com.backend.studentsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(info = @Info(title = "User API", version = "1.0", description = "API for managing users"))
@SpringBootApplication
public class StudentsearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentsearchApplication.class, args);
	}
}
