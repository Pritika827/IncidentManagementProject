package com.transline;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.transline.entities.Incidents;
import com.transline.repositories.IncidentRepository;
import com.transline.services.IncidentServices;

@Configuration
public class JpaConfig {

//	@Bean
//	public ModelMapper modelMapper() {
//		ModelMapper modelMapper = new ModelMapper();
//		modelMapper.getConfiguration().setPreferNestedProperties(false);
//		return modelMapper;
//	}

//	@Bean
//	public ObjectMapper objectMapper() {
//		ObjectMapper objectMapper = new ObjectMapper();
//		objectMapper.registerModule(new JavaTimeModule());
//
//		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//		objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
//		return objectMapper;
//	}

	public static ObjectMapper objectMapper(IncidentRepository incidentService) {
		ObjectMapper mapper = new ObjectMapper();
		SimpleModule module = new SimpleModule();
		module.addDeserializer(Incidents.class, new IncidentDeserializer(incidentService));
		mapper.registerModule(module);
		return mapper;
	}
}
