package com.transline;

import java.io.IOException;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.transline.entities.Incidents;
import com.transline.repositories.IncidentRepository;
import com.transline.services.IncidentServices;

public class IncidentDeserializer extends JsonDeserializer<Incidents> {

	private final IncidentRepository repository;

	public IncidentDeserializer(IncidentRepository repository) {
		this.repository = repository;
	}

	 @Override
	    public Incidents deserialize(JsonParser p, DeserializationContext ctxt)
	            throws IOException, JsonProcessingException {
	        String incidentId = p.getValueAsString();
	        return repository.findById(incidentId)
	                              .orElseThrow(() -> new IllegalArgumentException("Incident not found for ID: " + incidentId));
	    }}
