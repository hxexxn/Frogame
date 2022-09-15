package com.frogame.www;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication (exclude = {SecurityAutoConfiguration.class})
public class FrogameApplication {

	public static void main(String[] args) {
		SpringApplication.run(FrogameApplication.class, args);
	}

}
