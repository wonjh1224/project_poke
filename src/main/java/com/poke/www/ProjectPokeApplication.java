package com.poke.www;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ProjectPokeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectPokeApplication.class, args);
	}

}
