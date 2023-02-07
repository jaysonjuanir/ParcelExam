package com.exam.parcel;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.exam.parcel.util.ParcelRuleProperties;

@SpringBootApplication
public class ParcelApplication {

	public static void main(String[] args) {

		SpringApplication.run(ParcelApplication.class, args);
	}

	@Bean
	public CommandLineRunner initializeRule() {
		return args -> {

			System.out.println("Initializing! ");
			ParcelRuleProperties.initializeRuleValues();

		};
	}
}
