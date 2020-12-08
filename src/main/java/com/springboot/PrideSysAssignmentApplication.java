package com.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@ComponentScan({"com.springboot.*"})
@EnableJpaAuditing
public class PrideSysAssignmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrideSysAssignmentApplication.class, args);
	}

}
