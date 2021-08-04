package com.sticast.configurations.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

// TODO Implement log with AOP

@SpringBootApplication
@EnableJpaRepositories("com.sticast.repository")
@EntityScan("com.sticast.entity")
@ComponentScan("com.sticast")
public class SpringBootConfig extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringBootConfig.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootConfig.class, args);
	}


}
