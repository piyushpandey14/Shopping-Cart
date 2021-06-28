package com.ecommerce.Ecommerce;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EcommerceApplication {

	private static final Logger LOGGER=LoggerFactory.getLogger(EcommerceApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
		
		LOGGER.info("Application Started !!");
	}

}
