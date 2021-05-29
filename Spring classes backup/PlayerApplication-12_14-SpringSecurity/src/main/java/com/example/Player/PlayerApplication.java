package com.example.Player;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class PlayerApplication {

/*	// not working in demo
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {// if you dont use noop use this
		return new BCryptPasswordEncoder();
	}
	*/
	public static void main(String[] args) {
		SpringApplication.run(PlayerApplication.class, args);
	}

}

