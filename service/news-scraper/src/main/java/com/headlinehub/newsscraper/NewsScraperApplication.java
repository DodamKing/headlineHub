package com.headlinehub.newsscraper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.headlinehub.newsscraper.config.SecurityConfig;

@SpringBootApplication
@Import(SecurityConfig.class)
public class NewsScraperApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewsScraperApplication.class, args);
	}
	
	
	// @Configuration
	// public class SecurityConfig  {
	// 	@Bean
	// 	public BCryptPasswordEncoder passwordEncoder() {
	// 		return new BCryptPasswordEncoder();
	// 	}
	// }

	// 	protected void configure(HttpSecurity http) throws Exception {
	// 		http
    //             .authorizeRequests()
	// 				.antMatchers("/").permitAll()
	// 				.anyRequest().authenticated()
	// 				.and()
	// 			.formLogin()
	// 				.loginPage("/login")
	// 				.permitAll()
	// 				.and()
	// 			.logout()
	// 				.permitAll();
	// 	}
	// }
}

