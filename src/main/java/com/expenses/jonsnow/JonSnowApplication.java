package com.expenses.jonsnow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class JonSnowApplication {

	public static void main(String[] args) {
		SpringApplication.run(JonSnowApplication.class, args);
	}

}
