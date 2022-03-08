package com.tsis.drs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class DrsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DrsApplication.class, args);
	}

}
