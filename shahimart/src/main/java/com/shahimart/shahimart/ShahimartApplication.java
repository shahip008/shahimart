package com.shahimart.shahimart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAwareImpl")
public class ShahimartApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShahimartApplication.class, args);
	}

}
