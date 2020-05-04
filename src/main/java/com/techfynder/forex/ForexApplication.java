package com.techfynder.forex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

import com.techfynder.forex.controller.CurrencyController;

@SpringBootApplication
public class ForexApplication implements CommandLineRunner {
	
	@Autowired
	private CurrencyController cController; 

	public static void main(String[] args) {
		SpringApplication.run(ForexApplication.class, args);
		System.out.println("Hi it's Adnan's Project");
	}

	@Override
	public void run(String... args) throws Exception {
		cController.getCurrency("HKD");
		cController.getCurrency("NZD");
		
	}

}
