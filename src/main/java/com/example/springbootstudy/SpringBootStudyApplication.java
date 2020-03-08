package com.example.springbootstudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SpringBootStudyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootStudyApplication.class, args);
	}

}
