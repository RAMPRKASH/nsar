package com.nova.nsar.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({ "com.nova" })
@EntityScan(basePackages = "com.nova.nsar")
@EnableJpaRepositories("com.nova.nsar.repository")
@EnableAutoConfiguration
public class Application {

	public static void main(String[] args) {
		
		System.out.println("****************** nsar starting ********************");
		SpringApplication.run(Application.class, args);
		System.out.println("****************** nsar started ********************");
	}
}
