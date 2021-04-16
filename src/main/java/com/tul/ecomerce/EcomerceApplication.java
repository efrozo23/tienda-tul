package com.tul.ecomerce;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EcomerceApplication {
	
	public static Logger logger = LoggerFactory.getLogger(EcomerceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(EcomerceApplication.class, args);
	}

}
