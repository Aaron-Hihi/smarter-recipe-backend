package com.smarterrecipe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SmarterrecipeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmarterrecipeApplication.class, args);
	}

}
