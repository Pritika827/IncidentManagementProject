package com.transline.services;

import java.util.List;

import com.transline.dtos.IncidentsDto;

public interface IncidentServices {

	IncidentsDto saveIncidents(IncidentsDto incidentsDto);

	List<IncidentsDto> getAllIncidentsDetails();

	IncidentsDto getIncidentById(String incidentId);

	IncidentsDto updateIncidentsDto(IncidentsDto incidentsDto, String incidentId);

	void deteleIncidents(String incidentsId);

//	Incidents createIncident(Incidents incident);
	
	List<String> getAllIncidentIds();
	
}
