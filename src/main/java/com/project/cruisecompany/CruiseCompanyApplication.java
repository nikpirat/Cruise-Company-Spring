package com.project.cruisecompany;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaRepositories(basePackages="com.project.cruisecompany.repository")
@EnableTransactionManagement
@EntityScan(basePackages="com.project.cruisecompany.model")
public class CruiseCompanyApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruiseCompanyApplication.class, args);
	}



}
