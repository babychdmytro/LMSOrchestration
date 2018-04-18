package com.gcit.lms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class LmsOrchestrationApplication {
	//test jenkins14
	public static void main(String[] args) {
		SpringApplication.run(LmsOrchestrationApplication.class, args);
		System.out.println("deplsdssssssssoysdaaaas");
	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
}
