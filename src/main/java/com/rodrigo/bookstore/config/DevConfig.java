package com.rodrigo.bookstore.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.rodrigo.bookstore.services.DBservice;

@Configuration
@Profile("dev")
public class DevConfig {
	
	@Autowired
	private DBservice dbservice;
	
	//pegando o valor se esta nome ou create
	@Value("${spring.jpa.hibernate.ddl-auto}") 
	private String stategy;
	
	@Bean
	public boolean insInstanciaBaseDeDados() {
		if(stategy.equals("create")) {
			this.dbservice.instanciandoBaseDeDados();
		}
		return false;
	}

}
