package br.gov.go.cursomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.gov.go.cursomc.services.DBService;
import br.gov.go.cursomc.services.EmailService;
import br.gov.go.cursomc.services.MockEmailService;

@Configuration
@Profile("teste")
public class TestConfig {
	
	@Autowired
	private DBService dbService;
	
	@Bean
	public boolean instantiateDataBase() throws ParseException{
		dbService.instantiateTestDataBase();
		return true;
	}
	@Bean
	public EmailService emailService(){
		return new MockEmailService();
	}

}
