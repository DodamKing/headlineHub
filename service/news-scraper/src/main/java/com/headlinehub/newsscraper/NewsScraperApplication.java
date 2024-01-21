package com.headlinehub.newsscraper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.headlinehub.newsscraper.config.SecurityConfig;

@SpringBootApplication
@Import(SecurityConfig.class)
public class NewsScraperApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewsScraperApplication.class, args);
	}
}

