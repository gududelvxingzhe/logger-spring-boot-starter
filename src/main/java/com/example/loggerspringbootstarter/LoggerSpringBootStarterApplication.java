package com.example.loggerspringbootstarter;

import com.example.loggerspringbootstarter.config.LogConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

//@EnableConfigurationProperties(LogConfigProperties.class)
@SpringBootApplication
public class LoggerSpringBootStarterApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(LoggerSpringBootStarterApplication.class, args);
	}
	
}
