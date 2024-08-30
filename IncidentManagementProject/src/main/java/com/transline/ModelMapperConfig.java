package com.transline;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.transline.dtos.PoliceRemarksDto;
import com.transline.entities.Incidents;
import com.transline.entities.PoliceRemarks;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // Configure mapping from PoliceRemarks to PoliceRemarksDTO
        modelMapper.addMappings(new PropertyMap<PoliceRemarks, PoliceRemarksDto>() {
            @Override
            protected void configure() {
                map().setIncidentId(source.getIncident().getIncidentId()); // Assuming getId() returns String
            }
        });

        // Configure mapping from PoliceRemarksDTO to PoliceRemarks
        modelMapper.addMappings(new PropertyMap<PoliceRemarksDto, PoliceRemarks>() {
            @Override
            protected void configure() {
                map().setIncident(new Incidents(source.getIncidentId())); // Create Incidents based on ID
            }
        });

        return modelMapper;
    }
}
