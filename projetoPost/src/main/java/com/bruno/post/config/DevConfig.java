package com.bruno.post.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bruno.post.service.DBService;

@Configuration
public class DevConfig {

	@Autowired
	private DBService dbService;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;
	
	@Bean
	public Boolean instantiateDataBase() throws Exception {
		
		if("create".equals(strategy)) {
			return false;
		}
		dbService.instantiateTestDataBase();
		return true;
	}
}
