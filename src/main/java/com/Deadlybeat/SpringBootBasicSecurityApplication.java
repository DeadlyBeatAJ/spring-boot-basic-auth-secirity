package com.Deadlybeat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
//added to enable Spring provided basic Authentication
@EnableWebSecurity
public class SpringBootBasicSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootBasicSecurityApplication.class, args);
	}

}
