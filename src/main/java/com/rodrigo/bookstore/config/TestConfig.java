package com.rodrigo.bookstore.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.rodrigo.bookstore.services.DBservice;

@Configuration //dizendo que essa classe e de configuracao
@Profile("test") //dizendo que aqui e o perfil de teste
public class TestConfig {

	@Autowired
	private DBservice dBservice; //chamando a classe instanciandoBaseDeDados
	
	//instanciando a base de dados
	@Bean
	public void InstanciaBaseDeDados() {
		this.dBservice.instanciandoBaseDeDados();
	}
}
