package com.teste.api.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelConfiguration {

	@Bean
	public ModelMapper getModel() {
		var modelMapper = new ModelMapper();
		
		return new ModelMapper();
		
	}
	
	
	
	
	
}
