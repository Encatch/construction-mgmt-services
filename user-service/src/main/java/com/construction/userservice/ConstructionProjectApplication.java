package com.construction.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.construction.userservice")
public class ConstructionProjectApplication {
	public static void main(String[] args) {
		SpringApplication.run(ConstructionProjectApplication.class, args);
	}
}
