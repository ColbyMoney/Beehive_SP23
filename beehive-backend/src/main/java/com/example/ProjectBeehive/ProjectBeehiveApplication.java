package com.example.ProjectBeehive;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.example.ProjectBeehive.repository")
@EntityScan
@EnableConfigurationProperties()
@ComponentScan({"com.example.ProjectBeehive.controller", "com.example.ProjectBeehive.service", "com.example.ProjectBeehive.repository", "com.example.ProjectBeehive.security",})
@AutoConfiguration
public class ProjectBeehiveApplication {

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
	public static void main(String[] args) {

		//default user == user
		//default password is in console when ran
		SpringApplication.run(ProjectBeehiveApplication.class, args);

	}
}
