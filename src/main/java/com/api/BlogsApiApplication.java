package com.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.api.model,com.api.config,com.api.service.impl,com.api.repository,com.api.filter,com.api.controller")
public class BlogsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogsApiApplication.class, args);
	}

}
