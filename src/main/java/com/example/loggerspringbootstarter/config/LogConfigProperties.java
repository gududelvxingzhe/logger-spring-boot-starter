package com.example.loggerspringbootstarter.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Slf4j
@Component
@ConfigurationProperties(prefix = "log.config")
public class LogConfigProperties {
	
	private Boolean enable;
	private String name;
	private String type;
}
