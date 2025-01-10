package com.example.dadada;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DadadaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DadadaApplication.class, args);
	}

}
