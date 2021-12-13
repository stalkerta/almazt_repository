package com.example.vfgarage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan({"com.example.vfgarage.controller","com.example.vfgarage.service","com.example.vfgarage.dao","com.example.vfgarage.model"})
public class VfGarageApplication {

	public static void main(String[] args) {
		SpringApplication.run(VfGarageApplication.class, args);
	}

}
